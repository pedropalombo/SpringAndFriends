package com.rest.webservices.restful_web_services.security;

import static org.springframework.security.config.Customizer.withDefaults;	// method to show a 'Login' pop-up

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

// class to configure the filters from 'Spring Security'
@Configuration	// for the Bean (filter) methods to be configured
public class SpringSecurityConfiguration {
	
	// defining the filters to be used
	// 'SecurityFilterChain' is related to the filters used by 'Spring Security'
	// OBS: this bypass the 'Login' screen, and relies solely on direct requests from clients
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {	// needs to be prepared to throw an exception since it's dealing with HTTP requests
		
		// only authorise the requests from those who are authenticated
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated()
		);
		
		// triggers a log-in pop-up if the user isn't logged-in yet
		http.httpBasic(withDefaults());
		
		// disabling CSRF so it's possible to send the requests without credentials
		http.csrf(
				csrf -> csrf.disable()
		);
		
		return http.build();	// if no config is set, it's possible to override the filters and disable the authentication hehehehe
	}
}
