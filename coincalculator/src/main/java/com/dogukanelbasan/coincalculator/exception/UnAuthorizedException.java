package com.dogukanelbasan.coincalculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException  extends CoinException {
    
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnAuthorizedException(String messageCode) {
    	super(messageCode);   
    }
	
	public UnAuthorizedException(Exception e) {
    	super(e);   
    }
}