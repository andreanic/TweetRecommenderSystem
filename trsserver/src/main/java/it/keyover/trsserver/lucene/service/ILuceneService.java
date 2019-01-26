package it.keyover.trsserver.lucene.service;

import java.util.List;

import it.keyover.trsserver.entity.Tweet;
import it.keyover.trsserver.entity.User;
import it.keyover.trsserver.exception.BaseException;
import it.keyover.trsserver.util.LuceneConstants;

public interface ILuceneService {
	public String createIndex() throws BaseException;
	public List<String> getRecommandedTweetsTwitterIdFromIndex(User user) throws BaseException;
	public List<String> getTweetsTwitterIdFromIndex(String query, List<String> categories, LuceneConstants searchType) throws BaseException;
}
