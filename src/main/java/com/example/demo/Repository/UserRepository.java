package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findById(int id);

    Optional<User> findByUsername(String username);

    User findByEmail(String email);

    @Query("SELECT u.id from User u where username = ?1")
    User findUserSessionId(String username);

    @Query("SELECT u.username from User u where username = ?1")
    String getUsername(String username);

    @Query("SELECT u.email from User u where email = ?1")
    String getEmail(String email);

    @Query("SELECT u.password from User u where email = ?1")
    String getPassword(String email);

    @Query("SELECT u.id from User u where username = ?1")
    String getId(String username);
}
