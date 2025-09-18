package com.rest.webservices.restful_web_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController	// injecting HTTP methods to the class
public class VersioningPersonController {

	// ==| URI |==
	// OBS: Twitter style
	// get info from a section of the URI
	
	@GetMapping(path="/v1/person")	// consuming the 'GET' method from 'RestController'
	public PersonV1 retrievePersonV1() {
		return new PersonV1("Bob Dylan");	// sending info as a Bean
	}
	
	@GetMapping(path="/v2/person")
	public PersonV2 retrievePersonV2() {
		return new PersonV2(new Name("Robbin", "Williams"));	// sending info to 'PersonV2' so it may send it to 'Name' to build the object
	}
	// ==| / |==
	
	// ==| Request Parameter |==
	// OBS: Amazon style
	// choose the version based on the path's parameters
	
	@GetMapping(path="/person", params = "version=1")	// setting URI's params w/ the version
	public PersonV1 retrievePersonV1RequestParameter() {
		return new PersonV1("Bob Dylan");	// sending info as a Bean
	}

	@GetMapping(path="/person", params="version=2")
	public PersonV2 retrievePersonV2RequestParameter() {
		return new PersonV2(new Name("Robbin", "Williams"));	// sending info to 'PersonV2' so it may send it to 'Name' to build the object
	}
	// ==| / |==
	
	// ==| Custom Header |==
	// OBS: Microsoft style
	// retrieve the correct version from the request's header
	
	@GetMapping(path="/person/header", headers = "X-API-VERSION=1")	// defining what the header needs to have to set the correct version
	public PersonV1 retrievePersonV1Header() {
		return new PersonV1("Bob Dylan");	// sending info as a Bean
	}
	
	@GetMapping(path="/person/header", headers="X-API-VERSION=2")
	public PersonV2 retrievePersonV2Header() {
		return new PersonV2(new Name("Robbin", "Williams"));	// sending info to 'PersonV2' so it may send it to 'Name' to build the object
	}
	// ==| / |==
	
	// ==| Media Type |==
	// OBS: GitHub style
	// selects the version based on the required content set on header
	
	// uses the 'Accept' key from the header (since it's a formatting value)
	@GetMapping(path="/person/accept", produces="application/vnd.company.app-v1+json")	// retrieving the needed content format to define the version
	public PersonV1 retrievePersonV1MediaType() {
		return new PersonV1("Bob Dylan");	// sending info as a Bean
	}   
	
	@GetMapping(path="/person/accept", produces="application/vnd.company.app-v2+json")
	public PersonV2 retrievePersonV2MediaType() {
		return new PersonV2(new Name("Robbin", "Williams"));
	}	
	
	// ==| / |==
}
