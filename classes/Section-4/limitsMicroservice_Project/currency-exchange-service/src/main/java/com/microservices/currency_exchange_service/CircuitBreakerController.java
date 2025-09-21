package com.microservices.currency_exchange_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import io.github.resilience4j.retry.annotation.Retry;

// class to manage the 'Circuit Breaker' logic
@RestController		// injecting REST methods onto it, since it's going to manage the requests in times of need
public class CircuitBreakerController {
	
	// setting a logger to be used
	Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	// sample API to be used
	@GetMapping(path = "/sample-api")
	@Retry(name = "sample-api", fallbackMethod="hardcodedResponse")	// setting the name of the API for configuring the amount of retries on 'application.properties'
	public String sampleApi() {
		
		// logging the info to the console
		logger.info("Sample API call received");
		
		// reaching for an non-existant API so it fails and we apply the 'Circuit Breaker' logic
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
		
		return forEntity.getBody();
	}
	
	// fallback method to be used when the 'Retries' aren't enough
	public String hardcodedResponse(Exception ex) {		// since it's a fallback method, it requires an Exception as a parameter
		return "fallback-response";
	}

}
