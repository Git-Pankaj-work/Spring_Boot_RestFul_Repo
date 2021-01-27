package com.myfriendapp.exception.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.myfriendapp.error.ErrorResponse;
import com.myfriendapp.exception.FriendNotfoundException;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {
	
   /* @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

	@ExceptionHandler(FriendNotfoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(FriendNotfoundException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Friend Not Found", details);
		return new ResponseEntity(error, HttpStatus.NOT_FOUND);
	}

}
