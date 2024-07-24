package com.springboot.tradetrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EntityScan(basePackages = "com.springboot.tradetrack.Models")
@EnableJpaRepositories(basePackages = "com.springboot.tradetrack.Dao")
public class TradetrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradetrackApplication.class, args);
	}

	@GetMapping("welcome")
	public String hello() {
		return "Hello World";
	}
}
