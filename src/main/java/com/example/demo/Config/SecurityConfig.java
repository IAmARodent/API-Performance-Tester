package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;

import com.example.demo.Service.JpaUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JpaUserDetailService jpaUserDetailService;

    public SecurityConfig(JpaUserDetailService jpaUserDetailService) {
        this.jpaUserDetailService = jpaUserDetailService;
    }

    @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
                 .authorizeHttpRequests((authz) -> authz
                                 .requestMatchers("/").permitAll()
                                 .requestMatchers("/signup").permitAll()
                                 .requestMatchers("/user/add").permitAll()
                                 .requestMatchers("/login").permitAll()
                                 .anyRequest().authenticated()
                 )
                 .csrf(AbstractHttpConfigurer::disable)
                 .userDetailsService(jpaUserDetailService)
                 .formLogin(login -> login
                         .loginPage("/login")
                         .defaultSuccessUrl("/dashboard", true));
            //.logout((logout) -> logout.logoutUrl("/logout"));
        return http.build();
    }

    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("testuser1")
                .password("{noop}tABwq6tdkrm7hyueYIbn4A==")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}123")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}