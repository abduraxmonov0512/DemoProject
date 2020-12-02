package com.uzdemy.uzDemy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class UzDemyApplication {

	public static void main(String[] args) {
		SpringApplication.run(UzDemyApplication.class, args);
	}

}
