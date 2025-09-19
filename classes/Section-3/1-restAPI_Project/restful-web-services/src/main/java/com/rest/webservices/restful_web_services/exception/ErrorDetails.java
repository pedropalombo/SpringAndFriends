package com.rest.webservices.restful_web_services.exception;

import java.time.LocalDateTime;

//creating error Beans to use whenever it's triggered
public class ErrorDetails {
	
	// info to be used in the error
	private LocalDateTime timestamp;
	private String message,details;
	
	
	// == Auto generated constructor + getters ==
	public ErrorDetails(LocalDateTime timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
	// == | ==
	
	
}
