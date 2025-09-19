package com.microservices.limits_service.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// config class to get the values set on 'application.properties' for the values of other classes (aka LimitsController)
@Component		// tells Spring that this is a 'Bean' component
@ConfigurationProperties("limits-service")		// injecting methods to bring the data from 'application.properties' (currently: 'limits-service)
public class Configuration {
	
	private int minimum, maximum;

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	
	
	
}
