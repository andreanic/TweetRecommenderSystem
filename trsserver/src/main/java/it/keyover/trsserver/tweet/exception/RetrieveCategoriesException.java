package it.keyover.trsserver.tweet.exception;

import it.keyover.trsserver.exception.BaseException;

public class RetrieveCategoriesException extends BaseException {
	public RetrieveCategoriesException() {
		this.hrMessage = "Error during categories retrieval";
	}
}
