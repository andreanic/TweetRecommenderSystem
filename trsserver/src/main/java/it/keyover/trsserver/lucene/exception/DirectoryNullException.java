package it.keyover.trsserver.lucene.exception;

import it.keyover.trsserver.exception.BaseException;

public class DirectoryNullException extends BaseException {
	public DirectoryNullException() {
		this.hrMessage = "Directory MUST not be null";
	}
}
