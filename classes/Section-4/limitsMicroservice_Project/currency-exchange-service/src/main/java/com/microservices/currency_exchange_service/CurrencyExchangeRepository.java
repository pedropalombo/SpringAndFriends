package com.microservices.currency_exchange_service;

import org.springframework.data.jpa.repository.JpaRepository;

// interface that extends DB's (JPA) methods to be used by the other classes
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long>{
	CurrencyExchange findByFromCountryAndToCountry(String fromCountry, String toCountry);		// creating a method like 'findById()', but uses 2 parameters instead
}
