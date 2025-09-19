package com.microservices.currency_exchange_service;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// class to talk to return the currencies that'll be used on the exchange
// PS: its structure is in Section-4/2-CurrencyMicroservice.md
@Entity(name = "currency_exchange")		// injecting the class with database methods, and creating a "table" with its given name & structure
public class CurrencyExchange {
	
	@Id		// setting this as the PK for the 'CurrencyExchange' table
	private Long id;
	
	@Column(name="currency_from")	// changing the name of the column
	private String fromCountry;
	
	@Column(name="currency_to")		// changing the name of the column
	private String toCountry;
	
	private BigDecimal conversionMultiple;
	
	private String environment;		// so we can apply 'Load Balancing' logic to the given instances of 'CurrencyExchange'
	
	// empty constructor so Spring can structure it as a Bean component
	public CurrencyExchange() {
		
	}

	// -- Auto generated constructor, getters/setters, and toString() --	
	public CurrencyExchange(Long id, String fromCountry, String toCountry,
			BigDecimal conversionMultiple) {
		super();
		this.id = id;
		this.fromCountry = fromCountry;
		this.toCountry = toCountry;
		this.conversionMultiple = conversionMultiple;
	}

	// == CurrencyExchange base structure ==
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFromCountry() {
		return fromCountry;
	}
	public void setFromCountry(String fromCountry) {
		this.fromCountry = fromCountry;
	}
	public String getToCountry() {
		return toCountry;
	}
	public void setToCountry(String toCountry) {
		this.toCountry = toCountry;
	}
	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}
	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}
	// == / ==
	
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	@Override
	public String toString() {
		return "CurrencyExchange [id=" + id + ", fromCountry=" + fromCountry + ", toCountry="
				+ toCountry + ", conversionMultiple=" + conversionMultiple + "]";
	}
	// -- / --
	
	
}
