package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Test;

public interface TestRepository extends JpaRepository<Test,Integer> {

}
