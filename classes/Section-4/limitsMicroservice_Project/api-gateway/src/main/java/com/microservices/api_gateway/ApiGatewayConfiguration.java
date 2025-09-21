package com.microservices.api_gateway;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// class used for configuring the routes
@Configuration		// injecting configuration methods onto the class
public class ApiGatewayConfiguration {

	// function to route/map the path/requests
	@Bean	// setting the method as a Bean since 'Configuration' works with Beans
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		
		// redirecting the routes based on their paths
		return builder
				.routes()
				// for "get" parameters
				.route(p -> p.path("/get")
						.filters(
								f -> f
								.addRequestHeader("MyHeader", "MyURI")
								.addRequestParameter("Parameter", "My Value")
						)
						.uri("http://httpbin.org:80"))
				// for 'currency-exchange'
				.route(p -> p.path("/currency-exchange/**") 
						.uri("lb://currency-exchange"))	// .. redirects to a path from 'Eureka' (lb:// ==> 'Load Balancer')
				// for 'currency-conversion'
				.route(p -> p.path("/currency-conversion/**")
						.uri("lb://currency-conversion"))
				// for the Feign proxy
				.route(p -> p.path("/currency-conversion-feign/**")
						.uri("lb://currency-conversion"))
				// fully re-routing the URL
				.route(p -> p.path("/currency-conversion-new/**")	// path that got requested
						.filters(f -> f.rewritePath(
								"/currency-conversion-new/(?<segment>.*)", 	// getting the path (and whatever comes after it)
								"/currency-conversion-feign/${segment}"))	// and setting as something else (and appending the segment from previous url)
						.uri("lb://currency-conversion")	// as well as pointing to Eureka's actual path value
				)
				.build();
	}
}
