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
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = {"http://localhost:8080", "http://64.23.222.181:8080"})
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
		String path = hi.getPath();
		JMeter jmeter = new JMeter();
        Parser.something(users,rampup,duration,url,path);
		String temp;
		TestResults results = null;
		try {
			temp = jmeter.runTest();
			String bye = UUID.randomUUID().toString();
			results = Parser.something2(bye);
			UploadDirectory.uploadDir("report-output", "api-load-tester-html-reports", bye, true, false);
			MakeAllObjectPublic.doIt(bye);
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
			//MakeAllObjectPublic.doIt();
		}
		catch (Exception ignore){
			ignore.printStackTrace();
		}
		return 1;
	}

	@GetMapping("/hi")
	public void testing()
	{
		try
		{
			JMeter jmeter = new JMeter();
			String temp = jmeter.runTest();
		}
		catch (Exception e)
		{
			
		}
	}
}
