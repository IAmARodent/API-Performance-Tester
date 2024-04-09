package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;

@RestController
@RequestMapping(value= "/user")
public class UserController {
    
    @Autowired
    UserRepository userRepository;

    @PostMapping("/add")
    public long addUser(@RequestBody User user) {
        userRepository.save(user);
        return user.getId();
    }

    @GetMapping("/get")
    public List<User> findAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @GetMapping("/findByEmail")
    public User findByEmail(String email) {
        User target = userRepository.findByEmail(email);
        return target;
    }


    @GetMapping("/email")
    public String getEmail(String email) {
        String target = userRepository.getEmail(email);
        return target;
    }
    
    @GetMapping("/username")
    public String getUsername(String username) {
        String target = userRepository.getUsername(username);
        return target;
    }

    @GetMapping("/password")
    public String getPassword(String email) {
        String target = userRepository.getPassword(email);
        return target;
    }

    @GetMapping("/id")
    public String getId(String email) {
        String target = userRepository.getId(email);
        return target;
    }
    
}
