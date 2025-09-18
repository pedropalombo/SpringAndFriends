package com.rest.webservices.restful_web_services.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rest.webservices.restful_web_services.user.UserNotFoundException;

// customising the info used on 'ErrorDetails' by extending 'ResponseEntity'
@ControllerAdvice // same as a 'Controller' annotation, but it's Spring's
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	// overwriting the exception's signature method so we can fill our custom one with the info we want
	@ExceptionHandler(Exception.class) // 'Exception' involves all exceptions
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {

		// setting the exception's data that'll be shown
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR); // sending the info together with a '500' error code
	}

	// using the basis above to set our 'UserNotFound' exception
	@ExceptionHandler(UserNotFoundException.class) // using our custom exception
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request)
			throws Exception {

		// setting the exception's data that'll be shown
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND); // sending the info together with a '404' error code
																						
	}

	// exception for 'User' creation with an invalid input
	@Override	// overriding 'ResponseEntityExceptionHandler' so it sends our payload instead of using a default structure
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest request
	) {

		// setting the info to be sent as a payload
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				// showing the amount of errors on input as well as which was the first one
				"Total errors: "+ex.getErrorCount()+" | First error: "+ex.getFieldError().getDefaultMessage(),
				request.getDescription(false)
		);

		return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST); // sending the info together with a '400' error code
		
		//return handleExceptionInternal(ex, null, headers, status, request);
	}

}
