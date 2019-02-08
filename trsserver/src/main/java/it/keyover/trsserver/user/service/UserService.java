package it.keyover.trsserver.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import it.keyover.trsserver.entity.Tweet;
import it.keyover.trsserver.entity.User;
import it.keyover.trsserver.exception.BaseException;
import it.keyover.trsserver.lucene.service.ILuceneService;
import it.keyover.trsserver.tweet.repository.TweetRepository;
import it.keyover.trsserver.user.exception.UserAlreadyExistException;
import it.keyover.trsserver.user.exception.UserRegistrationException;
import it.keyover.trsserver.user.exception.WrongCredentialException;
import it.keyover.trsserver.user.repository.UserRepository;
import it.keyover.trsserver.util.PropertyReader;
import it.keyover.trsserver.util.SessionHelper;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TweetRepository tweetRepository;
	
	@Autowired
	private ILuceneService luceneService;
	
	private static final Integer MAX_LIKED_TWEETS = 30;
	private static final Integer MAX_USER_KEYWORDS = 20;
	
	@Override
	public String registerUser(User user) throws BaseException {
		if(userRepository.findByUsername(user.getUsername()).isPresent()) {
			throw new UserAlreadyExistException();
		}
		
		if(user.getTweetsLiked() != null && !user.getTweetsLiked().isEmpty()) {
			List<Tweet> tweets = tweetRepository.findByTwitteridIn(user.getTweetsLiked());
		}
		
		User savedUser = null;
		
		try {
			savedUser = userRepository.save(user);
		}catch(Exception e) {
			throw new UserRegistrationException();
		}
		
		if(savedUser != null) {
			return savedUser.getId();
		}
		else {
			throw new UserRegistrationException();
		}
	}

	@Override
	public String login(User user) throws BaseException {
		Optional<User> repoUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if(repoUser.isPresent()) {
			SessionHelper.setUserIntoSession(repoUser.get());
			return "Hello " + user.getUsername() + "!";
		}
		else {
			throw new WrongCredentialException();
		}
	}

	@Override
	public List<Tweet> getLikedTweets(String username) throws BaseException {
		User user = userRepository.findByUsername(username).get();
		
		List<String> tweetIds = user.getTweetsLiked().subList(0, Math.min(user.getTweetsLiked().size(),MAX_LIKED_TWEETS));
		List<Tweet> tweetsLiked = tweetRepository.findByTwitteridIn(tweetIds);
		
		return tweetsLiked;
	}
	

	@Override
	public List<Tweet> getRecommendedTweets(User user) throws BaseException {
		List<String> twitterids = new ArrayList<String>();
		List<String> twitteridsNoKeyword = new ArrayList<String>();
		for(String category : user.getPreferences()) {
			twitteridsNoKeyword.addAll(luceneService.getRecommandedTweetsTwitterIdFromIndex(category, user.getTweetsLiked()));
			twitterids.addAll(luceneService.getRecommandedTweetsTwitterIdFromIndexByKeywords(category, user.getTweetsLiked(), user.getKeywords()));
		}
		List<Tweet> tweetsFound;
		if(twitterids.isEmpty()) {
			tweetsFound = tweetRepository.findByTwitteridIn(twitteridsNoKeyword);
		}
		else {
			tweetsFound = tweetRepository.findByTwitteridIn(twitterids);
		}
		
		return tweetsFound;
	}
	
	@Override
	public void addLikeToTweet(User user, Tweet tweet) throws BaseException {
		user.getTweetsLiked().add(tweet.getTwitterid());
		if(!user.getPreferences().contains(tweet.getCategory())) {
			user.getPreferences().add(tweet.getCategory());
		}

		User userSaved = userRepository.save(user);
		SessionHelper.setUserIntoSession(userSaved);
	}
	
	@Override
	public void addTokenKeywordsToUser(User user, String query) throws BaseException{
		if(user.getKeywords() == null) {
			user.setKeywords(new ArrayList<String>());
		}
		
		setKeywords(user,query);
		
		User savedUser = userRepository.save(user);
		SessionHelper.setUserIntoSession(savedUser);
	}
	
	private void setKeywords(User user, String query) throws BaseException{
		List<String> keywords = luceneService.getTokensFromQuery(query);
		for(String keyword : keywords) {
			if(user.getKeywords().contains(keyword)) {
				user.getKeywords().remove(keyword);
			}
			user.getKeywords().add(keyword);
		}
		if(user.getKeywords().size() > MAX_USER_KEYWORDS) {
			do {
				user.getKeywords().remove(0);
			}while(user.getKeywords().size() > MAX_USER_KEYWORDS);
		}
	}
}
