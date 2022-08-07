package com.dogukanelbasan.coincalculator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("health")
public class HealthCheckController {
    
    @GetMapping
    public String isOk() {
        return "OK";
    }
}
