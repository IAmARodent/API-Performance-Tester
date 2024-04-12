package com.example.demo.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Model.SecurityUser;
import com.example.demo.Repository.UserRepository;

@Service
public class JpaUserDetailService implements UserDetailsService{

    private final UserRepository userRepository;

    public JpaUserDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
        .findByUsername(username)
        .map(SecurityUser::new)
        .orElseThrow(()-> new UsernameNotFoundException("Username not found: " + username));
    }
    
}
