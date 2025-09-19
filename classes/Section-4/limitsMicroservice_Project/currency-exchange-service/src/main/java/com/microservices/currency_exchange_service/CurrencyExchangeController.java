package com.microservices.currency_exchange_service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController		// injecting HTTP methods to the class
public class CurrencyExchangeController {
	
	@Autowired		// forcing the injection of the methods for 'Environment' to be done by Spring
	private Environment environment;
	
	@GetMapping(path="/currency-exchange/from/{from_country}/to/{to_country}")
	public CurrencyExchange retrieveExchangeValue(
			@PathVariable String from_country,
			@PathVariable String to_country
	) {
		
		// 2nd iteration: (manually) setting the return value for the request
		CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from_country, to_country, BigDecimal.valueOf(50));
		
		String port = environment.getProperty("local.server.port");		// fetching the port said instance of the microservice is running on
		
		currencyExchange.setEnvironment(port);	// adding the port to the response
		
		return currencyExchange;	// and returning it
		
		//return new CurrencyExchange(1000L, from_country, to_country, BigDecimal.valueOf(50));	// 1st iteration: returning a hard-coded Bean based on 'CurrencyExchange'
		
	}

}
