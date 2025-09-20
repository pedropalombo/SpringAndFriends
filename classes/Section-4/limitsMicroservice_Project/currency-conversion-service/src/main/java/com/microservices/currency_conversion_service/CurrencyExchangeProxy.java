package com.microservices.currency_conversion_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// interface to be used as a connector (Proxy) between 'CurrencyExchange' & 'CurrencyConversion'
// PS: it's needed by Feign to facilitate the requests
//@FeignClient(name="currency-exchange", url="http://localhost:8000")	// 1st instance: pointing to the 'app.name' as well as the port of the desired client ('CurrencyExchange')
@FeignClient(name="currency-exchange")	// 2nd instance: pointing to the 'app.name', and letting 'Eureka' do the load balancing
public interface CurrencyExchangeProxy {
	
	// creating the bridges for the methods by cloning the wanted methods, just changing the return values ('CurrencyExchange' -> 'CurrencyConversion'
	// this is the definition of a proxy
	@GetMapping(path="/currency-exchange/from/{from_country}/to/{to_country}")
	public CurrencyConversion retrieveExchangeValue(
			@PathVariable String from_country,
			@PathVariable String to_country
	);

}