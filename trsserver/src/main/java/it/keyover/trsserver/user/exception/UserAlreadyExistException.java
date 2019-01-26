package it.keyover.trsserver.user.exception;

import it.keyover.trsserver.exception.BaseException;

public class UserAlreadyExistException extends BaseException{
	public UserAlreadyExistException() {
		this.hrMessage = "Username already exists!";
	}
}
