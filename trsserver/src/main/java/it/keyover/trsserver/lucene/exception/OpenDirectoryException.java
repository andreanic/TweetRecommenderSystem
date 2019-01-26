package it.keyover.trsserver.lucene.exception;

import it.keyover.trsserver.exception.BaseException;

public class OpenDirectoryException extends BaseException {
	public OpenDirectoryException() {
		this.hrMessage = "Failed to open index directory";
	}
}
