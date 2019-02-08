package it.keyover.trsserver.lucene.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopFieldCollector;
import org.apache.lucene.search.grouping.GroupingSearch;
import org.apache.lucene.search.grouping.TopGroups;
import org.apache.lucene.search.grouping.TopGroupsCollector;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.TFIDFSimilarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Attribute;
import org.apache.lucene.util.BytesRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.keyover.trsserver.entity.Tweet;
import it.keyover.trsserver.entity.User;
import it.keyover.trsserver.exception.BaseException;
import it.keyover.trsserver.lucene.controller.LuceneController;
import it.keyover.trsserver.lucene.exception.AddToIndexException;
import it.keyover.trsserver.lucene.exception.AddWildCardFilterException;
import it.keyover.trsserver.lucene.exception.DirectoryNullException;
import it.keyover.trsserver.lucene.exception.IndexReaderOpenException;
import it.keyover.trsserver.lucene.exception.IndexSearchException;
import it.keyover.trsserver.lucene.exception.IndexWriterCloseException;
import it.keyover.trsserver.lucene.exception.IndexWriterCreationException;
import it.keyover.trsserver.lucene.exception.InvalidSearchTypeException;
import it.keyover.trsserver.lucene.exception.OpenDirectoryException;
import it.keyover.trsserver.lucene.exception.ReadDocumentFromIndexException;
import it.keyover.trsserver.lucene.exception.TokenizationException;
import it.keyover.trsserver.lucene.model.SearchTypeDTO;
import it.keyover.trsserver.lucene.repository.ILuceneRepository;
import it.keyover.trsserver.mapper.TweetToLuceneDocumentMapper;
import it.keyover.trsserver.tweet.repository.TweetRepository;
import it.keyover.trsserver.user.repository.UserRepository;
import it.keyover.trsserver.util.LuceneConstants;
import it.keyover.trsserver.util.PropertyReader;
import it.keyover.trsserver.util.QueryBuilder;

@Service
public class LuceneService implements ILuceneService {
	private static final Logger logger = LoggerFactory.getLogger(LuceneController.class);
	private static final StandardAnalyzer analyzer = new StandardAnalyzer();
	private static final String LUCENE_INDEX_SUCCESS="lucene.index.creation.success";
	private static final Integer TWEETS_LIMIT = 100;
	
	@Autowired
	private TweetRepository tweetRepository;
	@Autowired
	private ILuceneRepository luceneRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String createIndex() throws BaseException {
		
		List<Tweet> tweets = (List<Tweet>) tweetRepository.findAll();
		
		Directory directory = luceneRepository.getIndexDirectory();
		
		IndexWriter indexWriter = getIndexWriter(directory);
		
		for(Tweet tweet : tweets) {
			Document doc = TweetToLuceneDocumentMapper.map(tweet);
			try {
				indexWriter.addDocument(doc);
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new AddToIndexException(tweet.getTwitterid());
			}
		}
		
		
		try {
			indexWriter.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new IndexWriterCloseException();
		}
		
		return PropertyReader.getMessagesProperties(LUCENE_INDEX_SUCCESS);
	}

	@Override
	public List<String> getRecommandedTweetsTwitterIdFromIndex(String category, List<String> tweetsLiked) throws BaseException {
		return getRecommandedTweetsTwitterIdFromIndexByKeywords(category, tweetsLiked, null);
	}
	
	public List<String> getRecommandedTweetsTwitterIdFromIndexByKeywords(String category, List<String> tweetsLiked, List<String> keywords) throws BaseException{
		
		BooleanQuery.Builder builder = new BooleanQuery.Builder();
		
		QueryBuilder.addCategoryFilter(builder, category);
		
		QueryBuilder.addLikedTweetsFilter(builder,tweetsLiked);
		
		QueryBuilder.addKeywordsFilter(builder, keywords);
		
		BooleanQuery booleanQuery = builder.build();
		
		return getTwitteridFromIndexByQuery(booleanQuery, "", TWEETS_LIMIT);
	}

	@Override
	public List<String> getTweetsTwitterIdFromIndex(String query, String category, LuceneConstants searchType) throws BaseException {		
		String[] terms = query.split(" ");
		BooleanQuery.Builder builder = new BooleanQuery.Builder();
		
		
		if(searchType == null) {
			throw new InvalidSearchTypeException();
		}
		
		switch(searchType) {
			case EXACT_MATCH: QueryBuilder.addPhraseQuery(builder, TweetToLuceneDocumentMapper.TEXT,terms);break;
			case SIMILAR_MATCH: QueryBuilder.addFuzzyQuery(builder, TweetToLuceneDocumentMapper.TEXT, terms, Occur.MUST);break;
			default: throw new InvalidSearchTypeException();
		}
		
		if(category != null) {
			QueryBuilder.addCategoryFilter(builder,category);
		}
		
		return getTwitteridFromIndexByQuery(builder.build(),query,TWEETS_LIMIT);
	}
	
	public List<String> getTokensFromQuery(String query) throws BaseException{
		TokenStream tokenStream = analyzer.tokenStream("token", query);
		tokenStream = new StopFilter(tokenStream, EnglishAnalyzer.getDefaultStopSet());
		CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
		
		List<String> tokens = new ArrayList<String>();
		try {
			tokenStream.reset();
			while(tokenStream.incrementToken()) {
				tokens.add(charTermAttribute.toString());
			}
			
			tokenStream.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new TokenizationException(query);
		}
		
		return tokens;
	}
	
	//UTILITY METHODS
	private IndexWriter getIndexWriter(Directory directory) throws BaseException {
		if(directory == null) {
			throw new DirectoryNullException();
		}
		
		IndexWriterConfig writerConfig = new IndexWriterConfig(analyzer);
		IndexWriter indexWriter = null;
		try {
			indexWriter = new IndexWriter(directory, writerConfig);
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new IndexWriterCreationException();
		}
		
		return indexWriter;
	}
	
	private IndexReader getIndexReader(Directory directory) throws BaseException{
		IndexReader indexReader = null;
		try {
			indexReader = DirectoryReader.open(directory);
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new IndexReaderOpenException();
		}
		
		return indexReader;
	}
	
	private List<String> getTwitteridFromIndexByQuery(BooleanQuery booleanQuery, String originalQuery, Integer limit) throws BaseException{
		Directory directory = luceneRepository.getIndexDirectory();
		IndexReader indexReader = getIndexReader(directory);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		indexSearcher.setSimilarity(new BM25Similarity());
		
        TopDocs topDocs;
		try {
			topDocs = indexSearcher.search(booleanQuery, limit);
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new IndexSearchException(originalQuery);
		}
        List<String> twitterids = new ArrayList<String>();
	    for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
	    	try {
				Document doc = indexReader.document(scoreDoc.doc);
				twitterids.add(doc.get(TweetToLuceneDocumentMapper.TWITTERID));
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new ReadDocumentFromIndexException();
			}
	    }
        
		return twitterids;
	} 
}
