package it.keyover.trsserver.tweet.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import it.keyover.trsserver.common.model.ApiBaseResponse;
import it.keyover.trsserver.exception.AppException;
import it.keyover.trsserver.exception.BaseException;
import it.keyover.trsserver.exception.RestResponseEntityExceptionHandler;
import it.keyover.trsserver.tweet.exception.HelloWorldException;

@RestController
@RequestMapping("tweet")
public class TweetController extends RestResponseEntityExceptionHandler{

	@GET
	@RequestMapping("/helloworld/{id}")
	@ResponseBody
	public ResponseEntity<ApiBaseResponse> helloWorld(HttpServletRequest request, @PathVariable("id") Integer id) throws AppException {
		if(id.intValue() > 10) {
			ApiBaseResponse<String> apr = new ApiBaseResponse<String>(request.getRequestURI(),"Hello World!");
	        return new ResponseEntity<ApiBaseResponse>(apr,HttpStatus.OK);
		}
		else
			throw new AppException(new HelloWorldException("ID must be grater than 10"));
	}
}
