package it.keyover.trsserver.mapper;

import it.keyover.trsserver.entity.TwitterUser;
import it.keyover.trsserver.exception.BaseException;
import it.keyover.trsserver.mapper.exception.SourceNullException;
import twitter4j.User;

public class UserToTwitterUserMapper {
	public static TwitterUser map(User user) throws BaseException{
		
		if(user == null) {
			throw new SourceNullException(user.getClass().getName());
		}
		
		TwitterUser twitterUser = new TwitterUser();
		twitterUser.setId(user.getId());
		twitterUser.setName(user.getName());
		twitterUser.setScreenName(user.getScreenName());
		twitterUser.setVerified(user.isVerified());
		twitterUser.setUrlMiniImg(user.getMiniProfileImageURL());
		
		return twitterUser;
	}
}
