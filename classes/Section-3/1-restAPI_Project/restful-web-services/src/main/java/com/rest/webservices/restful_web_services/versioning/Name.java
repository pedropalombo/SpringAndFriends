package com.rest.webservices.restful_web_services.versioning;

// "Name" class to facilitate on versioning the application
public class Name {
	private String firstName, lastName;

	// ==| Auto-generated constructor/getter/toString() |==
	public Name(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return "PersonV2 [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	// ==|| / ||==
}
