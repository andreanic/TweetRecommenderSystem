package it.keyover.trsserver.lucene.exception;

import it.keyover.trsserver.exception.BaseException;

public class InvalidSearchTypeException extends BaseException {
	public InvalidSearchTypeException() {
		this.hrMessage = "Invalid search type input";
	}
}
