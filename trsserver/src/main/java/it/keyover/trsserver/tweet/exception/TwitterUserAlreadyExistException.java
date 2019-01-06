package it.keyover.trsserver.tweet.exception;

import it.keyover.trsserver.exception.BaseException;

public class TwitterUserAlreadyExistException extends BaseException {
	public TwitterUserAlreadyExistException() {
		this.hrMessage = "Twitter user already exist into collection";
	}
}
