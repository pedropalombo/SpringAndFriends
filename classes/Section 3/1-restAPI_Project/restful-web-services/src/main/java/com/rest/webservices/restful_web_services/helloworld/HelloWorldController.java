package com.rest.webservices.restful_web_services.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


// !! Section 3 - Ep. 17 !!
//Controller to be used to manage the main class' methods (REST API)
@RestController //annotation to enable REST methods to be used and values to be converted to web-based ones (Beans)
public class HelloWorldController {
	//for '/hello-world' path request
	// \-> return 'Hello World' string (GET request)
	@RequestMapping(method = RequestMethod.GET, path = "/hello-world")	//linking|mapping the HTTP method to our function, as well as the respective path
	public String helloWorld() {
		return "Hello World";
	}
	
	// \-> a better way of mapping methods
	@GetMapping(path="/hello-world-plus")
	public String helloWorldPlus() {
		return "Improved Hello World";
	}
	
	// \-> using Beans (Spring) logic to print the message
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello world as a bean"); //it converts the string to a JSON, and then prints it
	}
	
	// \-> applying Path Parameters to the Bean logic
	@GetMapping(path="/hello-world/path-variables/{name}")
	public HelloWorldBean helloWorldPathVariables(@PathVariable String name) { //mapping the value used on the URL to a variable
		return new HelloWorldBean(
				String.format("Hello world to you, %s", name)
		);
	}
}
