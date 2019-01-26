package it.keyover.trsserver.lucene.exception;

import it.keyover.trsserver.exception.BaseException;

public class IndexSearchException extends BaseException {
	public IndexSearchException(String query) {
		this.hrMessage = "Error during query processing. Query: " + query;
	}
}
