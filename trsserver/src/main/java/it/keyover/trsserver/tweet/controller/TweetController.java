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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import it.keyover.trsserver.entity.Tweet;
import it.keyover.trsserver.exception.AppException;
import it.keyover.trsserver.exception.BaseException;
import it.keyover.trsserver.exception.ExceptionHandlerController;
import it.keyover.trsserver.model.ApiBaseResponse;
import it.keyover.trsserver.tweet.model.SearchDTO;
import it.keyover.trsserver.tweet.service.ITweetService;
import it.keyover.trsserver.tweet.service.TweetService;

@RestController
@RequestMapping("tweet")
public class TweetController extends ExceptionHandlerController{

	private static final Logger logger = LoggerFactory.getLogger(TweetController.class);
	@Autowired
	ITweetService tweetService;
	
	@GET
	@RequestMapping("/retrieve/{category}")
	@ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiBaseResponse> retrieveTweets(HttpServletRequest request,@PathVariable("category") String category) throws AppException {
		try {
			Integer tweetsRetrieved = tweetService.retrieveTweets(category);
			ApiBaseResponse<String> apr = new ApiBaseResponse<String>(request.getRequestURI(),"Retrieved " + tweetsRetrieved + " new tweets using database collection");
	        return new ResponseEntity<ApiBaseResponse>(apr,HttpStatus.OK);
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
			Integer tweetsRetrieved = tweetService.retrieveTweet(screenName, category);
			ApiBaseResponse<String> apr = new ApiBaseResponse<String>(request.getRequestURI(),"Retrieved " + tweetsRetrieved + " new tweets from " + screenName);
	        return new ResponseEntity<ApiBaseResponse>(apr,HttpStatus.OK);
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
			ApiBaseResponse<String[]> apr = new ApiBaseResponse<String[]>(request.getRequestURI(),categories);
	        return new ResponseEntity<ApiBaseResponse>(apr,HttpStatus.OK);
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
			ApiBaseResponse<List<Tweet>> apr = new ApiBaseResponse<List<Tweet>>(request.getRequestURI(),tweets);
	        return new ResponseEntity<ApiBaseResponse>(apr,HttpStatus.OK);
		} catch (BaseException e) {
			throw new AppException(e);
		}
	}
	
	@POST
	@RequestMapping("/getTweetsByQueryAndCategory")
	@ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiBaseResponse> getTweetsByQueryAndCategory(HttpServletRequest request, @RequestBody SearchDTO search) throws AppException {
		try {
			List<Tweet> tweets = tweetService.getTweetsByQueryAndCategory(search);
			ApiBaseResponse<List<Tweet>> apr = new ApiBaseResponse<List<Tweet>>(request.getRequestURI(),tweets);
	        return new ResponseEntity<ApiBaseResponse>(apr,HttpStatus.OK);
		} catch (BaseException e) {
			throw new AppException(e);
		}
	}
}

