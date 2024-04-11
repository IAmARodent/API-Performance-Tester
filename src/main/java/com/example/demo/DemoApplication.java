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
		JMeter jmeter = new JMeter();
        Parser p = new Parser("nba.jmx", "results.csv"); 
        JMXProperties j = p.parseJMXProperties();
		String temp;
		/**
		if(server = americas)
		{
			run normal test; will run on current server since this server is located in americas already
		}
		else if(server = europe)
		{
			return "www.europedigitalocean.239487234/test" mock europe server ip
		}
		else if(server = asia)
		{
			return "www.asiadigitalocean.394353/test"  mock asia server ip
		}
		*/
		try {
			temp = jmeter.runTest();
		} catch (Exception ignore) {
			temp = "temp";
		}
		return temp;
	}
}
