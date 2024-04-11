package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.example.demo.Model.Test;
import com.example.demo.Repository.TestRepository;

@Controller
public class TestingController {
	@Autowired
    TestRepository testRepository;

	@PostMapping("/testing/userinputs")
    public void addUser(@RequestBody Test test) {
		System.out.println("ajlksdjfalsdjfad\n");
		System.out.println(test.getUserid());
		testRepository.save(test);
    }

	@GetMapping("/testing")
	public String testing(){
		return "usertesting";
	}
}