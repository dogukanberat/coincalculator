package com.dogukanelbasan.coincalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EntityScan(basePackages = { "com.dogukanelbasan.coincalculator.main.entity"})

public class CoincalculatorApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CoincalculatorApplication.class, args);
	}

}
