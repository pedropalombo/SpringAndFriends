package com.rest.webservices.restful_web_services.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// resource class to be used for the filtering logic
//@JsonIgnoreProperties({"field1", "field3"})		// static - injection to filter-out specific properties of the response (tuned-up version of 'JsonIgnore')
@JsonFilter("SomeBeanFilter")	// dynamic - receives the given 'FilterProvider' as a filter list to be applied to the response
public class SomeBean {
	
	private String field1;
	
	//@JsonIgnore		// filtering-out one of the fields (Static filtering)
	private String field2;
	
	private String field3;
	
	// == Auto generated Constructor, getters, and toString() ==
	public SomeBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}	

	public String getField1() {
		return field1;
	}

	public String getField2() {
		return field2;
	}

	public String getField3() {
		return field3;
	}

	@Override
	public String toString() {
		return "SomeBean [field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + "]";
	}
	// == / ==
	
}
