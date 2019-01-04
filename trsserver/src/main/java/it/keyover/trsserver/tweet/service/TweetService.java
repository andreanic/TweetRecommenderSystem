package it.keyover.trsserver.tweet.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.keyover.trsserver.common.factory.TwitterClientFactory;
import it.keyover.trsserver.entity.User;
import it.keyover.trsserver.exception.BaseException;
import it.keyover.trsserver.tweet.exception.RetrieveTweetsException;
import it.keyover.trsserver.user.repository.UserRepository;
import it.keyover.trsserver.util.PropertyReader;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

@Service
public class TweetService implements ITweetService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Integer retrieveTweets() throws BaseException{
		try {		
			Twitter twitter = TwitterClientFactory.getTwitterClient();
			List<Status> statuses = twitter.getUserTimeline("rogerfederer");
		    System.out.println("Showing home timeline.");
		    for (Status status : statuses) {
		        System.out.println(status.getUser().getName() + ":" + status.getText());
		    }
		    return statuses.size();
		} catch (TwitterException e) {
			throw new RetrieveTweetsException(e.getErrorMessage());
		}
	}

}
