package it.keyover.trsserver.tweet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TweetController {

	@RequestMapping("/helloworld")
	@ResponseBody
	public ResponseEntity<String> helloWorld() {
		return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
	}
}
