package it.keyover.trsserver.lucene.exception;

import it.keyover.trsserver.exception.BaseException;

public class IndexReaderOpenException extends BaseException {
	public IndexReaderOpenException() {
		this.hrMessage = "Failed to open index reader";
	}
}
