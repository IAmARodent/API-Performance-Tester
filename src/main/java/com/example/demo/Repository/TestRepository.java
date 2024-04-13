package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Model.Test;

public interface TestRepository extends JpaRepository<Test,Integer> {
    @Query("SELECT t from Test t where t.userid= ?1 order by t.datetime desc")
    List<Test> findByUserid(int userid);
}
