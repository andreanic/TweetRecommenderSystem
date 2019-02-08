package it.keyover.trsserver.lucene.service;

import java.util.List;

import it.keyover.trsserver.entity.Tweet;
import it.keyover.trsserver.entity.User;
import it.keyover.trsserver.exception.BaseException;
import it.keyover.trsserver.lucene.model.SearchTypeDTO;
import it.keyover.trsserver.util.LuceneConstants;

public interface ILuceneService {
	public String createIndex() throws BaseException;
	public List<String> getRecommandedTweetsTwitterIdFromIndex(String category, List<String> tweetsLiked) throws BaseException;
	public List<String> getRecommandedTweetsTwitterIdFromIndexByKeywords(String category, List<String> tweetsLiked, List<String> keywords) throws BaseException;
	public List<String> getTweetsTwitterIdFromIndex(String query, String category, LuceneConstants searchType) throws BaseException;
	public List<String> getTokensFromQuery(String query) throws BaseException;
}
