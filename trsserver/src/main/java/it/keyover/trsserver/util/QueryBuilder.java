package it.keyover.trsserver.util;

import java.util.List;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.BooleanClause.Occur;

import it.keyover.trsserver.mapper.TweetToLuceneDocumentMapper;

public class QueryBuilder {
	private static final Integer MAX_TERMS = 6;
	
	public static void addPhraseQuery(BooleanQuery.Builder bbuilder,String field,String[] terms) {	
		PhraseQuery.Builder pbuilder = new PhraseQuery.Builder();
		Integer limit = Math.min(terms.length, MAX_TERMS);
		for(Integer i = 0; i < limit; i++) {
			 pbuilder.add(new Term(field, terms[i]), i);
		}
       
        bbuilder.add(pbuilder.build(),Occur.MUST);
	}
	
	public static void addFuzzyQuery(BooleanQuery.Builder bbuilder, String field, String[] terms, Occur occur){	
		Integer limit = Math.min(terms.length, MAX_TERMS);
		for(Integer i = 0; i < limit; i++) {
			FuzzyQuery fuzzyQuery = new FuzzyQuery(new Term(field, terms[i]));
			bbuilder.add(fuzzyQuery, occur);
		}		
	}
	
	public static void addCategoryFilter(BooleanQuery.Builder builder,String category) {
		if(category == null || category == "") return;
		TermQuery termQuery = new TermQuery(new Term(TweetToLuceneDocumentMapper.CATEGORY,category));
		builder.add(termQuery,Occur.FILTER);
	}
	
	public static void addLikedTweetsFilter(BooleanQuery.Builder builder,List<String> twitterids) {
		if(twitterids.isEmpty()) return;
		
		for(String twitterid : twitterids) {
			TermQuery termQuery = new TermQuery(new Term(TweetToLuceneDocumentMapper.TWITTERID,twitterid));
			builder.add(termQuery,Occur.MUST_NOT);
		}	
	}
	
	public static void addKeywordsFilter(BooleanQuery.Builder builder, List<String> keywords) {
		if(keywords == null || keywords.isEmpty()) return;

		builder.setMinimumNumberShouldMatch(1);
		for(String keyword : keywords) {
			TermQuery termQuery = new TermQuery(new Term(TweetToLuceneDocumentMapper.TEXT,keyword));
			builder.add(termQuery,Occur.SHOULD);
		}
	}
}
