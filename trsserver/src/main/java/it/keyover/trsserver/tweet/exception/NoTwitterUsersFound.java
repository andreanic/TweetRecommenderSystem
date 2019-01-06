package it.keyover.trsserver.tweet.exception;

import it.keyover.trsserver.exception.BaseException;

public class NoTwitterUsersFound extends BaseException {
	public NoTwitterUsersFound() {
		this.hrMessage = "No twitter users found into collection";
	}
}
