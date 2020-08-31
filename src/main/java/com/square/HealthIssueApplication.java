package com.square;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.square.model.UsersModel;
import com.square.service.IUserService;


@SpringBootApplication
public class HealthIssueApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(HealthIssueApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(HealthIssueApplication.class);
	}

}
