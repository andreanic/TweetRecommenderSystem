package it.keyover.trsserver.tweet.exception;

import it.keyover.trsserver.exception.BaseException;

public class HashtagAlreadExistException extends BaseException {
	public HashtagAlreadExistException() {
		this.hrMessage = "Hashtag already exist into collection";
	}
}
