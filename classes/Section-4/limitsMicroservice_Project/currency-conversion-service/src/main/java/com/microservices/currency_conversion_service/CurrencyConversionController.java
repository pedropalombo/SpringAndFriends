package com.microservices.currency_conversion_service;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController		// injecting HTTP methods to the class
public class CurrencyConversionController {
	
	@GetMapping(path="/currency-conversion/from/{from_country}/to/{to_country}/quantity/{quantity}")
	public CurrencyConversion retrieveConversion(
			@PathVariable String from_country,
			@PathVariable String to_country,
			@PathVariable BigDecimal quantity
	) {
		
		return new CurrencyConversion(1000L, from_country, to_country, quantity, BigDecimal.ONE, BigDecimal.ONE,"");	// 1st iteration: sending a hard-coded Bean as a result
		
	}
}
