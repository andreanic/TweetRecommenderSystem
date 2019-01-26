package it.keyover.trsserver.lucene.exception;

import it.keyover.trsserver.exception.BaseException;

public class IndexWriterCloseException extends BaseException {
	public IndexWriterCloseException() {
		this.hrMessage = "Failed to close index writer";
	}
}
