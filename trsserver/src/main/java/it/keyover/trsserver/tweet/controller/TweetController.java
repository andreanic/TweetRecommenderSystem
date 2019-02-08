package it.keyover.trsserver.tweet.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import it.keyover.trsserver.entity.Tweet;
import it.keyover.trsserver.entity.User;
import it.keyover.trsserver.exception.AppException;
import it.keyover.trsserver.exception.BaseException;
import it.keyover.trsserver.exception.ExceptionHandlerController;
import it.keyover.trsserver.model.ApiBaseResponse;
import it.keyover.trsserver.tweet.model.SearchDTO;
import it.keyover.trsserver.tweet.service.ITweetService;
import it.keyover.trsserver.tweet.service.TweetService;
import it.keyover.trsserver.user.service.IUserService;

@RestController
@RequestMapping("tweet")
public class TweetController extends ExceptionHandlerController{

	private static final Logger logger = LoggerFactory.getLogger(TweetController.class);
	@Autowired
	ITweetService tweetService;
	@Autowired
	IUserService userService;
	
	@GET
	@RequestMapping("/remove")
	@ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiBaseResponse> remove(HttpServletRequest request) throws AppException {

			Integer tweetsRetrieved = tweetService.removeShortUrlTweets();
			ApiBaseResponse<String> abp = new ApiBaseResponse<String>(request.getRequestURI(),"Removed " + tweetsRetrieved + " tweets from database collection");
	        return new ResponseEntity<ApiBaseResponse>(abp,HttpStatus.OK);
		
	}
	
	@GET
	@RequestMapping("/retrieve/{category}")
	@ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiBaseResponse> retrieveTweets(HttpServletRequest request,@PathVariable("category") String category) throws AppException {
		try {
			Integer tweetsRetrieved = tweetService.retrieveTweetsFromCategory(category);
			ApiBaseResponse<String> abp = new ApiBaseResponse<String>(request.getRequestURI(),"Retrieved " + tweetsRetrieved + " new tweets using database collection");
	        return new ResponseEntity<ApiBaseResponse>(abp,HttpStatus.OK);
		} catch (BaseException e) {
			throw new AppException(e);
		}
		
	}
	
	@GET
	@RequestMapping("/usertimeline/{screenName}/{category}")
	@ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiBaseResponse> retrieveTweets(HttpServletRequest request, @PathVariable("screenName") String screenName, @PathVariable("category") String category) throws AppException {
		try {
			Integer tweetsRetrieved = tweetService.retrieveTweetsFromUser(screenName, category);
			ApiBaseResponse<String> abp = new ApiBaseResponse<String>(request.getRequestURI(),"Retrieved " + tweetsRetrieved + " new tweets from " + screenName);
	        return new ResponseEntity<ApiBaseResponse>(abp,HttpStatus.OK);
		} catch (BaseException e) {
			throw new AppException(e);
		}
		
	}
	
	@GET
	@RequestMapping("/getCategories")
	@ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiBaseResponse> getCategories(HttpServletRequest request) throws AppException {
		try {
			String[] categories = tweetService.getCategories();
			ApiBaseResponse<String[]> abp = new ApiBaseResponse<String[]>(request.getRequestURI(),categories);
	        return new ResponseEntity<ApiBaseResponse>(abp,HttpStatus.OK);
		} catch (BaseException e) {
			throw new AppException(e);
		}
	}
	
	@GET
	@RequestMapping("/getOneTweetByCategory")
	@ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiBaseResponse> getOneTweetByCategory(HttpServletRequest request) throws AppException {
		try {
			List<Tweet> tweets = tweetService.getOneTweetByCategory();
			ApiBaseResponse<List<Tweet>> abp = new ApiBaseResponse<List<Tweet>>(request.getRequestURI(),tweets);
	        return new ResponseEntity<ApiBaseResponse>(abp,HttpStatus.OK);
		} catch (BaseException e) {
			throw new AppException(e);
		}
	}
	
	@POST
	@RequestMapping("/getTweetsByQueryAndCategory")
	@ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiBaseResponse> getTweetsByQueryAndCategory(HttpServletRequest request, Authentication auth, @RequestBody SearchDTO search) throws AppException {
		try {
			User user = (User) auth.getPrincipal();
			List<Tweet> tweets = tweetService.getTweetsByQueryAndCategory(search);
			userService.addTokenKeywordsToUser(user, search.getQuery());
			ApiBaseResponse<List<Tweet>> abp = new ApiBaseResponse<List<Tweet>>(request.getRequestURI(),tweets);
	        return new ResponseEntity<ApiBaseResponse>(abp,HttpStatus.OK);
		} catch (BaseException e) {
			throw new AppException(e);
		}
	}
	
	@POST
	@RequestMapping("/getTweetsByQueryAndUserPreferences")
	@ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiBaseResponse> getTweetsByQueryAndUserPreferences(HttpServletRequest request, Authentication auth, @RequestBody SearchDTO search) throws AppException {
		try {
			User user = (User) auth.getPrincipal();
			List<Tweet> tweets = tweetService.getTweetsByQueryAndUserPreferences(search, user);
			userService.addTokenKeywordsToUser(user, search.getQuery());
			ApiBaseResponse<List<Tweet>> abp = new ApiBaseResponse<List<Tweet>>(request.getRequestURI(),tweets);
	        return new ResponseEntity<ApiBaseResponse>(abp,HttpStatus.OK);
		} catch (BaseException e) {
			throw new AppException(e);
		}
	}
}

