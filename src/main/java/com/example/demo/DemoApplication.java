package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) 
	{
		SpringApplication.run(DemoApplication.class, args);
	}
	@GetMapping("/test")
	public String test()
	{
		JMeter pee = new JMeter();
		String hi;
		try {
			hi = pee.runTest();
		} catch (Exception ignore) {
			hi = "poop";
		}
		return hi;
	}
}
