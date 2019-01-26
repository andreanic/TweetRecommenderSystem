package it.keyover.trsserver.lucene.exception;

import it.keyover.trsserver.exception.BaseException;

public class AddToIndexException extends BaseException{
	public AddToIndexException(String twitterid) {
		this.hrMessage = "Failed to add tweet to index. Twitterid: "+ twitterid;
	}
}
