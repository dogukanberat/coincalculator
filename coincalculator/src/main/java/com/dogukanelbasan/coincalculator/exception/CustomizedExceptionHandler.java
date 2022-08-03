package com.dogukanelbasan.coincalculator.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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

	@Autowired
	private MessageSource messageSource;



	@ExceptionHandler(BusinessException.class)
	public final ResponseEntity<Map<String, Object>> handleBusinessException(BusinessException ex, WebRequest request) {
		String message = ex.getMessage(messageSource, request.getLocale());
		Map<String, Object> errorAttributes = new LinkedHashMap<String, Object>();
		errorAttributes.put("message", message);
		
		log.error(" Status Code:" + HttpStatus.UNPROCESSABLE_ENTITY.value()
				+ " " + HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase() + " Error Message:" + message);

		return new ResponseEntity<>(errorAttributes, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(UnAuthorizedException.class)
	public final ResponseEntity<Map<String, Object>> handleUnAuthorizedExceptions(UnAuthorizedException ex, WebRequest request) {
		String message = ex.getMessage(messageSource, request.getLocale());
		Map<String, Object> errorAttributes = new LinkedHashMap<String, Object>();
		errorAttributes.put("message", message);
		
		log.error("UnAuthorizedException TraceId:"  + " Status Code:" + HttpStatus.UNAUTHORIZED.value()
				+ " " + HttpStatus.UNAUTHORIZED.getReasonPhrase() + " Error Message:" + message);

		return new ResponseEntity<>(errorAttributes, HttpStatus.UNAUTHORIZED);
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
		return new ResponseEntity<>(errorAttributes, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
