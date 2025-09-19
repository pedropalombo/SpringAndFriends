package com.microservices.limits_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.limits_service.bean.Limits;
import com.microservices.limits_service.configuration.Configuration;

// class to config the HTTP methods that'll be used
@RestController
public class LimitsController {
	
	@Autowired		// it forces the injection of the given class's methods (Configuration) into 'LimitsController'
	private Configuration configuration;	// retrieving the methods from the 'Configuration' class that fetches the values from 'application.properties'

	// retrieving all limits
	@GetMapping(path="/limits")
	public Limits retrieveLimits() {
		
		// getting the values for the variables from the 'Configuration' class, instead of hard-coding them
		return new Limits(configuration.getMinimum(), configuration.getMaximum());	// returning a Bean using 'Configuration's methods of retrieving data from 'application.properties'
	}
	
}
