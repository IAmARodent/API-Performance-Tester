package com.example.demo.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int testid;

    @Column
    private int userid;

    @Column
    private String datetime;

    @Column
    private String url;

    @Column
    private int users;

    @Column
    private int rampup;

    @Column
    private int duration;

    @Column
    private String serverlocation;

    @Column
    private Double averageresponsetime;

    @Column
    private int successrate;

    @Column
    private String htmlreportlink;

    public Test(int userid, String datetime, String url, int users, int rampup, int duration,
            String serverlocation, Double averageresponsetime, int successrate, String htmlreportlink) {
        super();
        this.userid = userid;
        this.datetime = datetime;
        this.url = url;
        this.users = users;
        this.rampup = rampup;
        this.duration = duration;
        this.serverlocation = serverlocation;
        this.averageresponsetime = averageresponsetime;
        this.successrate = successrate;
        this.htmlreportlink = htmlreportlink;
    }
    public Test(){
        super();
    }

    public int getTestid() {
        return testid;
    }

    public void setTestid(int testid) {
        this.testid = testid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public int getRampup() {
        return rampup;
    }

    public void setRampup(int rampup) {
        this.rampup = rampup;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getServerlocation() {
        return serverlocation;
    }

    public void setServerlocation(String serverlocation) {
        this.serverlocation = serverlocation;
    }

    public Double getAverageresponsetime() {
        return averageresponsetime;
    }

    public void setAverageresponsetime(Double averageresponsetime) {
        this.averageresponsetime = averageresponsetime;
    }

    public int getSuccessrate() {
        return successrate;
    }

    public void setSuccessrate(int successrate) {
        this.successrate = successrate;
    }

    public String getHtmlreportlink() {
        return htmlreportlink;
    }

    public void setHtmlreportlink(String htmlreportlink) {
        this.htmlreportlink = htmlreportlink;
    }

    
}