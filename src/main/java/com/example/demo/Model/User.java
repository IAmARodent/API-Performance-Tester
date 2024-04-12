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

  @Column(unique=true)
  private String username;

  @Column(unique=true)
  private String email;

  @Column
  private String password;

  @Column
  private String roles;

  public User(String username, String email, String password, String roles){
    super();
    this.username = username;
    this.email = email;
    this.password = password;
    this.roles = roles;
  }

  public User(){
    super();
  }


  public String getRoles() {
    return roles;
  }
  public void setRoles(String roles) {
    this.roles = roles;
  }

   public Integer getId() {
     return id;
   }
 
   public void setId(Integer id) {
     this.id = id;
   }
 
   public String getUserame() {
     return username;
   }
 
   public void setUsername(String username) {
     this.username = username;
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