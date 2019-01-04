package it.keyover.trsserver.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.keyover.trsserver.entity.User;
import it.keyover.trsserver.exception.BaseException;
import it.keyover.trsserver.user.exception.UserRegistrationException;
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

}
