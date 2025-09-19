package com.rest.webservices.restful_web_services.user;

import org.springframework.web.bind.annotation.RestController;

import com.rest.webservices.restful_web_services.jpa.PostRepository;

@RestController		// injecting HTTP methods to the class
public class PostResource {
	
	PostRepository repository;
	
}
