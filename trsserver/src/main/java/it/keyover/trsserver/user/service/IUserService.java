package it.keyover.trsserver.user.service;

import java.util.List;

import it.keyover.trsserver.entity.Tweet;
import it.keyover.trsserver.entity.User;
import it.keyover.trsserver.exception.BaseException;

public interface IUserService {
	public String registerUser(User user) throws BaseException;
	public String login(User user) throws BaseException;
	public List<Tweet> getLikedTweets(String username) throws BaseException;
}
