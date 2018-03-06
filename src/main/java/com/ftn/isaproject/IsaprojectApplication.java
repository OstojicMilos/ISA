package com.ftn.isaproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"rest", "service"})
@EnableJpaRepositories("repository")
@EntityScan("model")
public class IsaprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsaprojectApplication.class, args);
	}
}
