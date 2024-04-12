package com.example.demo;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.Model.TestInputs;
import com.example.demo.Model.TestResults;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) 
	{
		SpringApplication.run(DemoApplication.class, args);
	}
	@PostMapping("/test")
	public TestResults test(@RequestBody TestInputs hi)
	{
		int users = hi.getUsers();
		int rampup = hi.getRampup();
		int duration = hi.getDuration(); 
		String url = hi.getUrl();
		JMeter jmeter = new JMeter();
        Parser.something(users,rampup,duration,url);
		String temp;
		TestResults results = null;
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
			String bye = UUID.randomUUID().toString();
			results = Parser.something2(bye);
			UploadDirectory.uploadDir("report-output", "api-load-tester-html-reports", bye, true, false);
			MakeAllObjectPublic.doIt();
		} catch (Exception ignore) {
			temp = "temp";
		}

		return results;
	}

	@GetMapping("/uploadtest")
	public int uploadtest()
	{
		try{
			String hi = UUID.randomUUID().toString();
			UploadDirectory.uploadDir("report-output", "api-load-tester-html-reports", hi, true, false);
			MakeAllObjectPublic.doIt();
		}
		catch (Exception ignore){
			ignore.printStackTrace();
		}
		return 1;
	}
}
