package it.keyover.trsserver.tweet.exception;

import it.keyover.trsserver.exception.BaseException;

public class TweetsNotFoundException extends BaseException {
	public TweetsNotFoundException() {
		this.hrMessage = "No tweets found into collection.";
	}
}
