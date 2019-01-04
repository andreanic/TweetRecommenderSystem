package it.keyover.trsserver.user.service;

import it.keyover.trsserver.entity.User;
import it.keyover.trsserver.exception.BaseException;

public interface IUserService {
	public String registerUser(User user) throws BaseException;
}
