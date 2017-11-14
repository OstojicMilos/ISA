package com.ftn.isaproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages={"rest service"})
@SpringBootApplication
public class IsaprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsaprojectApplication.class, args);
	}
}
