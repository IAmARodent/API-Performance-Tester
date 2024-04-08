package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;

@Controller
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
    public List<User> getUser() {
        List<User> userList = userRepository.findAll();
        return userList;
    }
    
    
}
