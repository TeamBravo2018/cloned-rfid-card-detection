package com.cit.locator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.cit.core","com.cit.locator"})
@SpringBootApplication
public class LocatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocatorApplication.class, args);
	}
}
