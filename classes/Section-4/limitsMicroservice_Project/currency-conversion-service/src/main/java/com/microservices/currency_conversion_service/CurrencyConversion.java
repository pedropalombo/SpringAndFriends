package com.microservices.currency_conversion_service;

import java.math.BigDecimal;

// structural class for 'CurrencyConversion' (Bean)
public class CurrencyConversion {
	private Long id;
	private String fromCountry,toCountry;
	private BigDecimal quantity;
	private BigDecimal conversionMultiple;
	private BigDecimal totalCalculatedAmout;
	private String environment;
	
	
	// empty constructor so Spring can modulate it (Beans)
	public CurrencyConversion() {
		
	}
	
	// == Auto-generated constructor, getters/setters, and toString() ==
	public CurrencyConversion(Long id, String fromCountry, String toCountry, BigDecimal quantity, BigDecimal conversionMultiple,
			BigDecimal totalCalculatedAmout, String environment) {
		super();
		this.id = id;
		this.fromCountry = fromCountry;
		this.toCountry = toCountry;
		this.quantity = quantity;
		this.conversionMultiple = conversionMultiple;
		this.totalCalculatedAmout = totalCalculatedAmout;
		this.environment = environment;
	}

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

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalCalculatedAmout() {
		return totalCalculatedAmout;
	}

	public void setTotalCalculatedAmout(BigDecimal totalCalculatedAmout) {
		this.totalCalculatedAmout = totalCalculatedAmout;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	@Override
	public String toString() {
		return "CurrencyConversion [id=" + id + ", fromCountry=" + fromCountry + ", toCountry=" + toCountry
				+ ", conversionMultiple=" + conversionMultiple + ", quantity=" + quantity + ", totalCalculatedAmout="
				+ totalCalculatedAmout + ", environment=" + environment + "]";
	}
	// == / ==	
	
}
