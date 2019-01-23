package it.keyover.trsserver.tweet.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

import it.keyover.trsserver.exception.AppException;
import it.keyover.trsserver.exception.BaseException;
import it.keyover.trsserver.exception.ExceptionHandlerController;
import it.keyover.trsserver.model.ApiBaseResponse;
import it.keyover.trsserver.tweet.service.ITweetService;

@RestController
@RequestMapping("tweet")
public class TweetController extends ExceptionHandlerController{

	@Autowired
	ITweetService tweetService;
	
	@GET
	@RequestMapping("/retrieve")
	@ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiBaseResponse> retrieveTweets(HttpServletRequest request) throws AppException {
		try {
			Integer tweetsRetrieved = tweetService.retrieveTweets();
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
}
