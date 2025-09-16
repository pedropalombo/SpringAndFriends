package com.rest.webservices.restful_web_services.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rest.webservices.restful_web_services.user.UserNotFoundException;

// customising the info used on 'ErrorDetails' by extending 'ResponseEntity'
@ControllerAdvice	//same as a 'Controller' annotation, but it's Spring's
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	// overwriting the exception's signature method so we can fill our custom one with the info we want
	@ExceptionHandler(Exception.class)	//'Exception' involves all exceptions
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
		
		// setting the exception's data that'll be shown
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				ex.getMessage(),
				request.getDescription(false)
		);
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);	//sending the info together with a '500' error code
	}
	
	// using the basis above to set our 'UserNotFound' exception
		@ExceptionHandler(UserNotFoundException.class)	//using our custom exception
		public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
			
			// setting the exception's data that'll be shown
			ErrorDetails errorDetails = new ErrorDetails(
					LocalDateTime.now(),
					ex.getMessage(),
					request.getDescription(false)
			);
			
			return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);	//sending the info together with a '404' error code
		}
	
	
}
