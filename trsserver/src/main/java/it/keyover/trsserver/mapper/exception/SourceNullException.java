package it.keyover.trsserver.mapper.exception;

import it.keyover.trsserver.exception.BaseException;

public class SourceNullException extends BaseException {
	public SourceNullException(String className) {
		this.hrMessage = "Source can't be null/empty for class " + className;
	}
}
