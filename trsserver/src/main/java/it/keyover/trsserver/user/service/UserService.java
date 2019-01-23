package it.keyover.trsserver.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import it.keyover.trsserver.entity.User;
import it.keyover.trsserver.exception.BaseException;
import it.keyover.trsserver.user.exception.UserRegistrationException;
import it.keyover.trsserver.user.exception.WrongCredentialException;
import it.keyover.trsserver.user.repository.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String registerUser(User user) throws BaseException {
		try {
			User savedUser = userRepository.save(user);
			
			if(savedUser != null) {
				return savedUser.getId();
			}
			else {
				throw new UserRegistrationException();
			}
		}catch(Exception e) {
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
			
			return "Login Successful";
		}
		else {
			throw new WrongCredentialException();
		}
	}

}
