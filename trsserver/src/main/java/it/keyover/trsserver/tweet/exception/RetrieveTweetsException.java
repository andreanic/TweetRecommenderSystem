package it.keyover.trsserver.tweet.exception;

import it.keyover.trsserver.exception.BaseException;

public class RetrieveTweetsException extends BaseException{
	public RetrieveTweetsException(String message) {
		this.hrMessage = message;
	}
}
