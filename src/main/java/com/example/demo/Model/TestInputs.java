package com.example.demo.Model;

public class TestInputs {
    private String url;
    private int users;
    private int rampup;
    private int duration;
    private String path;
    private String serverlocation;
    public String getUrl() {
        return url;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path =  path;
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

}
