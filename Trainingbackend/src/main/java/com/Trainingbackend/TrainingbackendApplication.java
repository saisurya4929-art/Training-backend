package com.Trainingbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.Trainingbackend","com.Training"})
public class TrainingbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingbackendApplication.class, args);
	}

}
