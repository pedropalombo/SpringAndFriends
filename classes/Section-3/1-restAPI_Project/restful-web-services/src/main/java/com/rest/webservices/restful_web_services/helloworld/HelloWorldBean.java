package com.rest.webservices.restful_web_services.helloworld;

//creating a Bean Service to be used by our Controller
public class HelloWorldBean {
	private String message;

	//instancing our variable that will consume the service
	public HelloWorldBean(String message) {
		this.message = message;
	}
	
	//debugging method since it had a "Constructor missing" error
	public HelloWorldBean() {
	}
	// --

	// GETTERS & SETTERS
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	// -- //

	// toString() for message debugging
	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}
	
	
}
