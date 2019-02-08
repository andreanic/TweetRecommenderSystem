package it.keyover.trsserver.lucene.exception;

import it.keyover.trsserver.exception.BaseException;

public class AddWildCardFilterException extends BaseException{
	public AddWildCardFilterException() {
		this.hrMessage = "Failed to add wildcard filtering";
	}
}
