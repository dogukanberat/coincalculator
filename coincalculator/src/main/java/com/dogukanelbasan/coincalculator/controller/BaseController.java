package com.dogukanelbasan.coincalculator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller("baseController")
public class BaseController {
	public <T> ResponseEntity responseEntity(T entity) {
		return ResponseEntity.status(HttpStatus.OK).body(entity);
	}

	public <T> ResponseEntity responseOK() {
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	public <T> ResponseEntity responseWithErrorStatus(HttpStatus status,T entity) {
		return ResponseEntity.status(status).body(entity);
	}
}
