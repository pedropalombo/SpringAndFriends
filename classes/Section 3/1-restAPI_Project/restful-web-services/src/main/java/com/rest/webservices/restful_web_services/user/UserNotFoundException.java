package com.rest.webservices.restful_web_services.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)	//defines the request status that'll use this
public class UserNotFoundException extends RuntimeException {
	
	// instantiating the error message through its constructor
	public UserNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
