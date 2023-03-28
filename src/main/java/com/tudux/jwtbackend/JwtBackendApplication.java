package com.tudux.jwtbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.tudux.jwtbackend.components")
public class JwtBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtBackendApplication.class, args);
	}

}
