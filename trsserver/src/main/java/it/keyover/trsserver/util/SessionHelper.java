package it.keyover.trsserver.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import it.keyover.trsserver.entity.User;

public class SessionHelper {
	public static void setUserIntoSession(User user) {
		Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
}
