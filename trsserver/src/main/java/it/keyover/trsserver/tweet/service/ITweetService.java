package it.keyover.trsserver.tweet.service;



import org.springframework.stereotype.Service;

import it.keyover.trsserver.exception.BaseException;


public interface ITweetService {
	public Integer retrieveTweets() throws BaseException;
}
