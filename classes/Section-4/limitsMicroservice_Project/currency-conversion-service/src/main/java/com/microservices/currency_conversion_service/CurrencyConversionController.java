package com.microservices.currency_conversion_service;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController		// injecting HTTP methods to the class
public class CurrencyConversionController {
	
	@GetMapping(path="/currency-conversion/from/{from_country}/to/{to_country}/quantity/{quantity}")
	public CurrencyConversion retrieveConversion(
			@PathVariable String from_country,
			@PathVariable String to_country,
			@PathVariable BigDecimal quantity
	) {
		
		// 2nd iteration: sending a request to 'CurrencyExchange' microservice to retrieve data and use as such
		// creating a HashMap so the placeholders from the request is populated with the ones from 'PathVariable'
		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from_country",from_country);
		uriVariables.put("to_country", to_country);
		
		// sending a request to 'CurrencyExchange', using the acquired 'PathVariables'
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from_country}/to/{to_country}",
				CurrencyConversion.class, 
				uriVariables
		);
		
		// setting the body of the response as the conversion
		CurrencyConversion currencyConversion = responseEntity.getBody();
		
		// returning the 'CurrencyConversion' based on the response from 'CurrencyExchange'
		return new CurrencyConversion(
			currencyConversion.getId(), 
			from_country, 
			to_country, 
			quantity, 
			currencyConversion.getConversionMultiple(), 
			quantity.multiply(currencyConversion.getConversionMultiple()), 
			currencyConversion.getEnvironment()
		);
		
		//return new CurrencyConversion(1000L, from_country, to_country, quantity, BigDecimal.ONE, BigDecimal.ONE,"");	// 1st iteration: sending a hard-coded Bean as a result
		
	}
}
