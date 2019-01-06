package it.keyover.trsserver.tweet.exception;

import it.keyover.trsserver.exception.BaseException;

public class UserNotVerifiedException extends BaseException{
	public UserNotVerifiedException() {
		this.hrMessage = "Unable to save user and/or user's tweets to collection/s. User is not verified";
	}
}
