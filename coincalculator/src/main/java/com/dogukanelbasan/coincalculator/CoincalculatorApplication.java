package com.dogukanelbasan.coincalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(value = { "com.dogukanelbasan.coincalculator" })
@EntityScan(basePackages = { "com.dogukanelbasan.coincalculator.entity"})
@EnableJpaRepositories(basePackages = { "com.dogukanelbasan.coincalculator.repository"})

public class CoincalculatorApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CoincalculatorApplication.class, args);
	}

}
