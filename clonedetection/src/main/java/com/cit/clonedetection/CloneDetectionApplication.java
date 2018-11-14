package com.cit.clonedetection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.cit.common","com.cit.clonedetection"})
@SpringBootApplication
public class CloneDetectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloneDetectionApplication.class, args);
	}
}
