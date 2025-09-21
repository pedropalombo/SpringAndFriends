package com.microservices.api_gateway;


import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Mono;

// class to filter the logs that'll be sent to the server
@Component		// setting it as a component so it gets managed by Spring
public class LoggingFilter implements GlobalFilter {
	
	// setting a logger 
	private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

	// unimplemented methods from 'GlobalFilter'
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		// attaching the info (in this case, the path for the web exchange
		logger.info("Path of the received request -> {}", exchange.getRequest().getPath());
		
		return chain.filter(exchange);
	}
	
	
}
