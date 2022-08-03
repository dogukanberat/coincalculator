package com.dogukanelbasan.coincalculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class BusinessException extends CoinException {

    
	public BusinessException(String messageCode, Object... messageArguments) {
        super(messageCode, messageArguments);
    }

    public BusinessException(Exception e, String messageCode, Object... messageArguments) {
        super(e, messageCode, messageArguments);
    }

    public BusinessException(Exception e) {
        super(e);
    }

}
