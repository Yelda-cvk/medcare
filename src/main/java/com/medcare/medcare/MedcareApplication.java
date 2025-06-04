package com.medcare.medcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.medcare.medcare.model") // 👈 Burası eklendi!
public class MedcareApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedcareApplication.class, args);
	}
}
