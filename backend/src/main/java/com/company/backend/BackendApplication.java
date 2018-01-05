package com.company.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

@EnableAutoConfiguration
@SpringBootApplication
public class BackendApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	
	 @Bean
	    public RequestContextListener requestContextListener() {
	        return new RequestContextListener();
	    }
}
