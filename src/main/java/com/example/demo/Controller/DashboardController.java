package com.example.demo.Controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@Controller
public class DashboardController {
	@GetMapping("/dashboard")
	public String dashboard(Model model){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "[ERROR COULD NOT RETRIEVE NAME.]";
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
			model.addAttribute("username", username);
		} else {
			username = principal.toString();
		}
		
		return "dashboard";
	}
}