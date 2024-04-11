package com.example.demo.Model;

public class TestResults {
    private String Date;
    private Double responseTime;
    private int successRate;
    private String htmlreportlink;
    public Double getResponseTime() {
        return responseTime;
    }
    public String getDate() {
        return Date;
    }
    public void setDate(String date) {
        Date = date;
    }
    public void setResponseTime(Double responseTime) {
        this.responseTime = responseTime;
    }
    public int getSuccessRate() {
        return successRate;
    }
    public void setSuccessRate(int successRate) {
        this.successRate = successRate;
    }
    public String getHtmlreportlink() {
        return htmlreportlink;
    }
    public void setHtmlreportlink(String htmlreportlink) {
        this.htmlreportlink = htmlreportlink;
    }

}
