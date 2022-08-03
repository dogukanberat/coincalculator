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

	public CoinException(Exception e) {
		super(e.getMessage(), e);
		this.messageCode = null;
		this.messageArguments = null;
	}
	
	public CoinException(Exception e, String messageCode, Object... messageArguments) {
		super(e.getMessage(), e);
		this.messageCode = messageCode;
		this.messageArguments = null;

	}

	public CoinException(String messageCode, Object... messageArguments) {
		this.messageCode = messageCode;
		this.messageArguments = messageArguments;
	}

	public Optional<String> getErrorCode() {
		return Optional.ofNullable(messageCode);
	}

	public String getMessage(MessageSource messageSource, Locale locale) {
		if (messageCode == null) {
			// this is a wrap exception, use original exception's message
			return this.getMessage();
		} else {
			return messageSource.getMessage(messageCode, messageArguments, locale);
		}
	}

}
