package com.stockmarket.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.stockmarket.helper.ErrorInfo;

@RestControllerAdvice
public class RegistrationExceptionHandler {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(RegistrationExceptionHandler.class);
	
	
	@ExceptionHandler(StockMarketException.class)
	public ResponseEntity<ErrorInfo> stockMarketExceptionHandler(StockMarketException exception){
		LOGGER.error(exception.getMessage(),exception);
		ErrorInfo errorInfo=new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorMessage((exception.getMessage()));
		return new ResponseEntity<>(errorInfo,HttpStatus.BAD_REQUEST);
		}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception){
		LOGGER.error(exception.getMessage(),exception);
		ErrorInfo errorInfo=new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorInfo.setErrorMessage("Something went wrong");
		return new ResponseEntity<>(errorInfo,HttpStatus.INTERNAL_SERVER_ERROR);
	}


	
}
