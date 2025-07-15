package com.hulkhiretech.payments.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(NullPointerException.class)
public ResponseEntity<?> nullPoiterExceptionHnadler(NullPointerException ex) {
	
	Map<String,String> error=new HashMap();
	error.put("erromsg", ex.getMessage());
	error.put("errorCode", ex.getErrorCode());
     return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
}
	
}
