package it.keyover.trsserver.tweet.exception;

import it.keyover.trsserver.exception.BaseException;

public class HelloWorldException extends BaseException {
	public HelloWorldException(String message) {
		this.hrMessage = message;
	}
}
