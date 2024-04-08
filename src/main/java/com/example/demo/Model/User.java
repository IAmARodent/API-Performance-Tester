package com.example.demo.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String username;

  @Column
  private String email;

  @Column
  private String password;

  public User(String username, String email, String password){
    super();
    this.username = username;
    this.email = email;
    this.password = password;
  }

   public Integer getId() {
     return id;
   }
 
   public void setId(Integer id) {
     this.id = id;
   }
 
   public String getName() {
     return username;
   }
 
   public void setName(String name) {
     this.username = name;
   }
 
   public String getEmail() {
     return email;
   }
 
   public void setEmail(String email) {
     this.email = email;
   }
 
   public String getPassword() {
     return password;
   }
 
   public void setPassword(String password) {
     this.password = password;
   }

}