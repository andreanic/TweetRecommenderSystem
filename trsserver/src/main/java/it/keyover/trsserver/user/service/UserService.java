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
import it.keyover.trsserver.tweet.repository.TweetRepository;
import it.keyover.trsserver.user.exception.UserAlreadyExistException;
import it.keyover.trsserver.user.exception.UserRegistrationException;
import it.keyover.trsserver.user.exception.WrongCredentialException;
import it.keyover.trsserver.user.repository.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TweetRepository tweetRepository;
	
	private static final Integer MAX_LIKED_TWEETS = 10;
	
	@Override
	public String registerUser(User user) throws BaseException {
		if(userRepository.findByUsername(user.getUsername()).isPresent()) {
			throw new UserAlreadyExistException();
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
		if(userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).isPresent()) {
			UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user, user.getPassword());
			AuthenticationManager authManager = new AuthenticationManager() {
				
				@Override
				public Authentication authenticate(Authentication authentication) throws AuthenticationException {
					// TODO Auto-generated method stub
					return authentication;
				}
			};
			Authentication auth = authManager.authenticate(authReq);
			SecurityContext sc = SecurityContextHolder.getContext();
			sc.setAuthentication(auth);
			
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

}
