package it.keyover.trsserver.user.exception;

import it.keyover.trsserver.exception.BaseException;

public class UserRegistrationException extends BaseException {
	public UserRegistrationException() {
		this.hrMessage = "Unable to store user on database";
	}
}
