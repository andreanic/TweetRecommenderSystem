package it.keyover.trsserver.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.keyover.trsserver.common.model.ApiBaseResponse;
import it.keyover.trsserver.entity.User;
import it.keyover.trsserver.exception.AppException;
import it.keyover.trsserver.exception.BaseException;
import it.keyover.trsserver.exception.ExceptionHandlerController;
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
	public ResponseEntity<ApiBaseResponse> retrieveTweets(HttpServletRequest request, @RequestBody User user) throws AppException {
		try {
			String userId = userService.registerUser(user);
			ApiBaseResponse<String> apr = new ApiBaseResponse<String>(request.getRequestURI(),"User save with ID: " + userId);
	        return new ResponseEntity<ApiBaseResponse>(apr,HttpStatus.OK);
		} catch (BaseException e) {
			throw new AppException(e);
		}
	}
}