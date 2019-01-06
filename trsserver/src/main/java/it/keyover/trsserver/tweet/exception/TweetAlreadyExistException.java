package it.keyover.trsserver.tweet.exception;

import it.keyover.trsserver.exception.BaseException;

public class TweetAlreadyExistException extends BaseException {
	public TweetAlreadyExistException() {
		this.hrMessage = "Tweet already exist into collection";
	}
}
