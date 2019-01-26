package it.keyover.trsserver.lucene.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.ArrayBuilders.BooleanBuilder;

import it.keyover.trsserver.entity.Tweet;
import it.keyover.trsserver.entity.User;
import it.keyover.trsserver.exception.BaseException;
import it.keyover.trsserver.lucene.controller.LuceneController;
import it.keyover.trsserver.lucene.exception.AddToIndexException;
import it.keyover.trsserver.lucene.exception.DirectoryNullException;
import it.keyover.trsserver.lucene.exception.IndexReaderOpenException;
import it.keyover.trsserver.lucene.exception.IndexSearchException;
import it.keyover.trsserver.lucene.exception.IndexWriterCloseException;
import it.keyover.trsserver.lucene.exception.IndexWriterCreationException;
import it.keyover.trsserver.lucene.exception.InvalidSearchTypeException;
import it.keyover.trsserver.lucene.exception.OpenDirectoryException;
import it.keyover.trsserver.lucene.exception.ReadDocumentFromIndexException;
import it.keyover.trsserver.lucene.repository.ILuceneRepository;
import it.keyover.trsserver.mapper.TweetToLuceneDocumentMapper;
import it.keyover.trsserver.tweet.repository.TweetRepository;
import it.keyover.trsserver.user.repository.UserRepository;
import it.keyover.trsserver.util.LuceneConstants;
import it.keyover.trsserver.util.PropertyReader;

@Service
public class LuceneService implements ILuceneService {
	private static final Logger logger = LoggerFactory.getLogger(LuceneController.class);
	private static final StandardAnalyzer analyzer = new StandardAnalyzer();
	private static final String LUCENE_INDEX_SUCCESS="lucene.index.creation.success";
	private static final Integer TWEETS_LIMIT = 30;
	
	@Autowired
	private TweetRepository tweetRepository;
	@Autowired
	private ILuceneRepository luceneRepository;
	@Autowired
	private UserRepository userRepository;
	
	@SuppressWarnings("resource")
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
	public List<String> getRecommandedTweetsTwitterIdFromIndex(User user) throws BaseException {
		BooleanQuery.Builder builder = new BooleanQuery.Builder();
		
		addCategoriesFilter(builder, user.getPreferences());
		
		addLikedTweetsFilter(builder,user.getTweetsLiked());
		
		BooleanQuery booleanQuery = builder.build();
		
		return getTwitteridFromIndexByQuery(booleanQuery, "");
	}

	@Override
	public List<String> getTweetsTwitterIdFromIndex(String query, List<String> categories, LuceneConstants searchType) throws BaseException {		
		String[] terms = query.split(" ");
		BooleanQuery.Builder builder = new BooleanQuery.Builder();

		switch(searchType) {
			case EXACT_MATCH: addPhraseQuery(builder, TweetToLuceneDocumentMapper.TEXT,terms);break;
			case SHOULD_SIMILAR_MATCH: addFuzzyQuery(builder, TweetToLuceneDocumentMapper.TEXT, terms, Occur.SHOULD);break;
			case MUST_SIMILAR_MATCH: addFuzzyQuery(builder, TweetToLuceneDocumentMapper.TEXT, terms, Occur.MUST);break;
			default: throw new InvalidSearchTypeException();
		}
		
		if(categories != null && !categories.isEmpty()) {
			addCategoriesFilter(builder,categories);
		}
		
		return getTwitteridFromIndexByQuery(builder.build(),query);
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
	
	private IndexSearcher getIndexSearcher(IndexReader indexReader) {
        return new IndexSearcher(indexReader);
	}
	
	private void addPhraseQuery(BooleanQuery.Builder bbuilder,String field,String[] terms) {
		
		
		PhraseQuery.Builder pbuilder = new PhraseQuery.Builder();
		for(Integer i = 0; i < terms.length; i++) {
			 pbuilder.add(new Term(field, terms[i]), i);
		}
       
        bbuilder.add(pbuilder.build(),Occur.MUST);
	}
	
	private void addFuzzyQuery(BooleanQuery.Builder bbuilder, String field, String[] terms, Occur occur){		
		for(String term : terms) {
			FuzzyQuery fuzzyQuery = new FuzzyQuery(new Term(field, term));
			bbuilder.add(fuzzyQuery, occur);
		}		
	}
	
	private void addCategoriesFilter(BooleanQuery.Builder builder,List<String> categories) {
		for(String category : categories) {
			TermQuery termQuery = new TermQuery(new Term(TweetToLuceneDocumentMapper.CATEGORY,category));
			builder.add(termQuery,Occur.FILTER);
		}	
	}
	
	private void addLikedTweetsFilter(BooleanQuery.Builder builder,List<String> twitterids) {
		for(String twitterid : twitterids) {
			TermQuery termQuery = new TermQuery(new Term(TweetToLuceneDocumentMapper.TWITTERID,twitterid));
			builder.add(termQuery,Occur.MUST_NOT);
		}	
	}
	
	private List<String> getTwitteridFromIndexByQuery(BooleanQuery booleanQuery, String originalQuery) throws BaseException{
		Directory directory = luceneRepository.getIndexDirectory();
		IndexReader indexReader = getIndexReader(directory);
		IndexSearcher indexSearcher = getIndexSearcher(indexReader);
		
        TopDocs topDocs;
		try {
			topDocs = indexSearcher.search(booleanQuery, TWEETS_LIMIT);
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
