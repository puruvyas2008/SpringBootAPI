package com.batch.springboot.common;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExecptionAdvice {
	
	

	@ExceptionHandler(NotFoundExecption.class)
	public ResponseEntity<CustomeErrorResponse> handleNotFound(NotFoundExecption e) {

		CustomeErrorResponse customeErrorResponse= new CustomeErrorResponse("NOT_FOUND_ERROR", e.getMessage());
		customeErrorResponse.setStatus(407);
		customeErrorResponse.setTimestamp(LocalDateTime.now());
		HttpHeaders headers= new HttpHeaders();
		headers.add("Content-Type", "Application/Json");
		headers.add("Myname", "Amit");
		
		return new ResponseEntity<CustomeErrorResponse>(customeErrorResponse,headers,HttpStatus.NOT_FOUND);
		
		
	}

}
