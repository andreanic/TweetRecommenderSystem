package it.keyover.trsserver.tweet.exception;

import it.keyover.trsserver.exception.BaseException;

public class RetrieveTweetsException extends BaseException{
	public RetrieveTweetsException() {
		this.hrMessage = "An error occurs while retrieving tweets";
	}
	
	public RetrieveTweetsException(String category) {
		this.hrMessage = "An error occurs while retrieving tweets for the following category: " + category;
	}
}
