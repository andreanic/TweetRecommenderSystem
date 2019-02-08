package it.keyover.trsserver.lucene.exception;

import it.keyover.trsserver.exception.BaseException;

public class TokenizationException extends BaseException {
	public TokenizationException(String query) {
		this.hrMessage = "Faild to tokenize query: " + query;
	}
}
