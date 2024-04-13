package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import java.util.List;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;

@RestController
@RequestMapping(value= "/user")
public class UserController {
    
    @Autowired
    UserRepository userRepository;

    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        String username = user.getUserame();
        String email = user.getEmail();
        String plaintext = user.getPassword();

        if (userRepository.getUsername(username) != null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username is already in use");
        } else {
            if (userRepository.getEmail(email) != null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email is already in use");
            }
            user.setPassword(BCrypt.hashpw(plaintext, BCrypt.gensalt()));
            userRepository.save(user);
            return "{\"message\": \"Registration Sucessful.\"}";
        }
    }
    
    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        /*
         * 
         String email = user.getEmail();
         String password = user.getPassword();
         User target = userRepository.findByEmail(email);
         String targetPass = target.getPassword();
         targetPass = gen.decrypt(targetPass);
         System.out.println(targetPass);
         if (password.equals(targetPass)){
             return "{\"message\": \"Login.\"}";
         } else {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect Email or Password!");
         }
         */
        return null;
    }

    @GetMapping("/get")
    public String findUserSession() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "[ERROR COULD NOT RETRIEVE NAME.]";
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
        String target = userRepository.getId(username);

        return target;
    }

    @GetMapping("/findByEmail")
    public User findByEmail(String email) {
        User target = userRepository.findByEmail(email);
        return target;
    }

    @GetMapping("/email")
    public String getEmail(String email) {
        String target = userRepository.getEmail(email);
        if (target == null){
            target = "{}";
        }
        return target;
    }
    
    @GetMapping("/username")
    public String getUsername(String username) {
        String target = userRepository.getUsername(username);
        if (target == null){
            target = "{}";
        }
        return target;
    }

    @GetMapping("/password")
    public String getPassword(String email) {
        String target = userRepository.getPassword(email);
        return target;
    }

    @GetMapping("/id")
    public String getId(String username) {
        String target = userRepository.getId(username);
        return target;
    }

    
}
