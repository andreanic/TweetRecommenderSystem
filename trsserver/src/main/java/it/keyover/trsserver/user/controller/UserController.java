package it.keyover.trsserver.user.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.keyover.trsserver.entity.Tweet;
import it.keyover.trsserver.entity.User;
import it.keyover.trsserver.exception.AppException;
import it.keyover.trsserver.exception.BaseException;
import it.keyover.trsserver.exception.ExceptionHandlerController;
import it.keyover.trsserver.model.ApiBaseResponse;
import it.keyover.trsserver.user.service.IUserService;

@RestController
@RequestMapping("user")
public class UserController extends ExceptionHandlerController{

	@Autowired
	private IUserService userService;
	
	@POST
	@RequestMapping("/save")
	@ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiBaseResponse> saveUser(HttpServletRequest request, @RequestBody User user) throws AppException {
		try {
			String userId = userService.registerUser(user);
	        return this.login(request, user);
		} catch (BaseException e) {
			throw new AppException(e);
		}
	}
	
	@POST
	@RequestMapping("/login")
	@ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiBaseResponse> login(HttpServletRequest request, @RequestBody User user) throws AppException {
		try {
			String success = userService.login(user);
			ApiBaseResponse<String> apr = new ApiBaseResponse<String>(request.getRequestURI(),success);
	        return new ResponseEntity<ApiBaseResponse>(apr,HttpStatus.OK);
		} catch (BaseException e) {
			throw new AppException(e);
		}
	}
	
	@GET
	@RequestMapping("/getLikedTweets")
	@ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiBaseResponse> getLikedTweets(HttpServletRequest request, Authentication auth) throws AppException {
		try {
			User user = (User) auth.getPrincipal();
			List<Tweet> tweetsLiked = userService.getLikedTweets(user.getUsername());
			ApiBaseResponse<List<Tweet>> apr = new ApiBaseResponse<List<Tweet>>(request.getRequestURI(),tweetsLiked);
			return new ResponseEntity<ApiBaseResponse>(apr,HttpStatus.OK);
		} catch (BaseException e) {
			throw new AppException(e);
		}
	}
}