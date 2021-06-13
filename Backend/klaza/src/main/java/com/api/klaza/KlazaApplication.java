package com.api.klaza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Collections;

@SpringBootApplication(scanBasePackages={"com.api.util","com.api.entities","com.api.daos","com.api.dtos","com.api.klaza"})
public class KlazaApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(KlazaApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "3000"));
		app.run(args);
	}
}
