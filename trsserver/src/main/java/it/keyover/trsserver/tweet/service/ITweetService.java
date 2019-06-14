package it.keyover.trsserver.tweet.service;

import java.util.List;

import it.keyover.trsserver.entity.Tweet;
import it.keyover.trsserver.entity.User;
import it.keyover.trsserver.exception.BaseException;
import it.keyover.trsserver.tweet.model.SearchDTO;
import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.TwitterException;

public interface ITweetService {
	public Integer retrieveTweetsFromCategory(String category) throws BaseException;
	public Integer retrieveTweetsFromUser(String screenName, String category) throws BaseException;
	public Long saveTweet(Status tweet, String category) throws BaseException;
	//public String saveHashtag(HashtagEntity hashtag) throws BaseException;
	public Long saveTwitterUser(String screenName, String category) throws BaseException;
	public String[] getCategories() throws BaseException;
	public List<Tweet> getOneTweetByCategory() throws BaseException;
	public List<Tweet> getTweetsByQueryAndCategory(SearchDTO search) throws BaseException;
	public List<Tweet> getTweetsByQueryAndUserPreferences(SearchDTO search, User user) throws BaseException;
	public Integer removeShortUrlTweets();
}
