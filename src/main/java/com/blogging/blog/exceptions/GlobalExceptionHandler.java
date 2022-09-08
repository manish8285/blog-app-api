package com.blogging.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blogging.blog.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFound(ResourceNotFoundException ex){
		String message = ex.getMessage();
		return new ResponseEntity<>(new ApiResponse(message,false),HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String,String> map = new HashMap<>();
		ex.getAllErrors().forEach(error->{
			String field = ((FieldError)error).getField();
			String defaultMessage = error.getDefaultMessage();
			
			map.put(field, defaultMessage);
			
		});
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse> apiException(ApiException ex){
		String message = ex.getMessage();
		return new ResponseEntity<>(new ApiResponse(message,true),HttpStatus.BAD_REQUEST);
	}
}
