package com.rest.webservices.restful_web_services.versioning;

public class PersonV2 {
	private Name name;	// creating a 'Name' class so whatever change is needed on the API, it won't impact on the attributes themselves
	
	// ==| Auto-generated constructor/getters/toString() |==
	// instantiating the constructor with the 'Name' class since its methods are going to be necessary (get/toString())
	public PersonV2(Name name) {
		super();
		this.name = name;
	}

	public Name getName() {
		return name;
	}

	@Override
	public String toString() {
		return "PersonV2 [name=" + name + "]";
	}
	// ==| / |==
	
		
}
