package it.keyover.trsserver.lucene.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.keyover.trsserver.exception.AppException;
import it.keyover.trsserver.exception.BaseException;
import it.keyover.trsserver.exception.ExceptionHandlerController;
import it.keyover.trsserver.lucene.service.ILuceneService;
import it.keyover.trsserver.model.ApiBaseResponse;
import it.keyover.trsserver.tweet.service.TweetService;

@RestController
@RequestMapping("lucene")
public class LuceneController extends ExceptionHandlerController {
	private static final Logger logger = LoggerFactory.getLogger(LuceneController.class);

	@Autowired
	private ILuceneService luceneService;
	
	@GET
	@RequestMapping("/createIndex")
	@ResponseBody
    @Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiBaseResponse> createIndex(HttpServletRequest request) throws AppException {
		try {
			String success = luceneService.createIndex();
			ApiBaseResponse<String> apr = new ApiBaseResponse<String>(request.getRequestURI(),success);
	        return new ResponseEntity<ApiBaseResponse>(apr,HttpStatus.OK);
		} catch (BaseException e) {
			throw new AppException(e);
		}
	}
}
