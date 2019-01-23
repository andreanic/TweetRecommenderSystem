package it.keyover.trsserver.tweet.service;

import it.keyover.trsserver.exception.BaseException;
import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.UserMentionEntity;


public interface ITweetService {
	public Integer retrieveTweets() throws BaseException;
	public Integer retrieveTweet(String screenName, String category) throws BaseException;
	public Long saveTweet(Status tweet, String category) throws BaseException;
	public String saveHashtag(HashtagEntity hashtag) throws BaseException;
	public Long saveTwitterUser(String screenName, String category) throws BaseException, TwitterException;
}
