package it.keyover.trsserver.lucene.exception;

import it.keyover.trsserver.exception.BaseException;

public class IndexWriterCreationException extends BaseException {
	public IndexWriterCreationException() {
		this.hrMessage = "Failed to create index writer";
	}
}
