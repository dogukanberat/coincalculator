package com.dogukanelbasan.coincalculator.exception;


import com.dogukanelbasan.coincalculator.constants.CurrencyConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger log = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(CoinException.class)
	public final ResponseEntity<Map<String, Object>> handleCoinExceptions(CoinException ex, WebRequest request) {
		Map<String, Object> errorAttributes = new LinkedHashMap<String, Object>();
		errorAttributes.put("timestamp", new Date());
		errorAttributes.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorAttributes.put("error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		errorAttributes.put("series", HttpStatus.INTERNAL_SERVER_ERROR.series().name());
		errorAttributes.put("message", CurrencyConstants.INVALID_DATA);
		errorAttributes.put("messageArguments", ex.getMessageArguments());
		return new ResponseEntity<>(errorAttributes, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex, WebRequest request) {
		log.error(" Status Code:" + HttpStatus.INTERNAL_SERVER_ERROR.value()
		+ " " + HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() + " Error Message:",ex);

		Map<String, Object> errorAttributes = new LinkedHashMap<String, Object>();
		errorAttributes.put("timestamp", new Date());
		errorAttributes.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorAttributes.put("error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		errorAttributes.put("series", HttpStatus.INTERNAL_SERVER_ERROR.series().name());
		errorAttributes.put("message", ex.getMessage());
		errorAttributes.put("messages", null);
		return new ResponseEntity<>(errorAttributes, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
