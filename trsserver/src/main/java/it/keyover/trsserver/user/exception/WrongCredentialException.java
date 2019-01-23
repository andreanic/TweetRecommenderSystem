package it.keyover.trsserver.user.exception;

import it.keyover.trsserver.exception.BaseException;

public class WrongCredentialException extends BaseException {
	public WrongCredentialException() {
		this.hrMessage = "Invalid username or password";
	}
}
