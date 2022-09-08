package com.example.ifuture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IfutureApplication {

	public static void main(String[] args) {
		SpringApplication.run(IfutureApplication.class, args);
	}

}
