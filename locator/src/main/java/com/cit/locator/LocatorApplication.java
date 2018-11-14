package com.cit.locator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.cit")
@SpringBootApplication
public class LocatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocatorApplication.class, args);
	}
}
