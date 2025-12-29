package com.store.customer.exception;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.store.customer.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> handle(CustomerNotFoundException exp) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exp.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exp) {
		var errors = new HashMap<String, String>();
		exp.getBindingResult().getAllErrors().forEach(err -> {
			var fieldName = ((FieldError) err).getField();
			var msg = err.getDefaultMessage();
			errors.put(fieldName, msg);
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
	}
}
