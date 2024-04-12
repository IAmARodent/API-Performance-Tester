package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String login(){
		return "login";
	}

	@GetMapping("/logout")
	public String logout(){
		return "login";
	}
}