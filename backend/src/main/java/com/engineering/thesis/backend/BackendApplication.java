package com.engineering.thesis.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.engineering.thesis.backend.repositories.PatientsRepository;

@EnableAutoConfiguration
@ComponentScan
@EnableJpaRepositories(basePackageClasses = PatientsRepository.class)
@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
