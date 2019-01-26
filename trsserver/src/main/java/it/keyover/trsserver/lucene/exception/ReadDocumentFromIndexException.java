package it.keyover.trsserver.lucene.exception;

import it.keyover.trsserver.exception.BaseException;

public class ReadDocumentFromIndexException extends BaseException {
	public ReadDocumentFromIndexException() {
		this.hrMessage = "Failed to read document from index";
	}
}
