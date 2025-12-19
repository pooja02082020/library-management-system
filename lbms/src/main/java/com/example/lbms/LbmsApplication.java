package com.example.lbms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LbmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LbmsApplication.class, args);
	}

}
