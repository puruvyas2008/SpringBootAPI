package com.batch.springboot.customers.common;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExecptionAdvices {

	@ExceptionHandler(NotFoundExecptions.class)
	public ResponseEntity<ErrorResponse> execptionHandler(NotFoundExecptions e) {

		ErrorResponse error = new ErrorResponse("NOT_FOUND_ERROR", e.getMessage());
error.setStatus(404);
		error.setLocalDateTime(LocalDateTime.now());

		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	}

}
