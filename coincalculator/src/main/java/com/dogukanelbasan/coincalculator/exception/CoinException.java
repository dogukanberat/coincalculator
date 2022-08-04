package com.dogukanelbasan.coincalculator.exception;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;
import java.util.Optional;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CoinException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String messageCode;
	private Object[] messageArguments;

	public CoinException(Exception e,String message) {
		super(message, e);
		this.messageCode = null;
		this.messageArguments = null;
	}

}
