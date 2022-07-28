package com.example.app1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example")
public class A1Application {

	public static void main(String[] args) {
		SpringApplication.run(A1Application.class, args);
	}

}
