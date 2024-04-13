package com.example.demo.REST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Test;
import com.example.demo.Repository.TestRepository;

@RestController
public class TestingRestController {
	@Autowired
    TestRepository testRepository;

	@GetMapping("/testing/list")
	public List<Test> findByUserId(int userid) {
		return testRepository.findByUserid(userid);
	}
	
}