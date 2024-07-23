package com.springboot.tradetrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TradetrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradetrackApplication.class, args);
	}

	@GetMapping("welcome")
	public String hello() {
		return "Hello World";
	}
}
