package com.dnakolor.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan(basePackages = {"com.dnakolor.api.services"})
@ComponentScan(basePackages = {"com.dnakolor.api.config"})
@ComponentScan(basePackages = {"com.dnakolor.api.filter"})
@ComponentScan(basePackages = {"com.dnakolor.api.Controllers"})
@ComponentScan(basePackages = {"com.dnakolor.api.repository"})
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
