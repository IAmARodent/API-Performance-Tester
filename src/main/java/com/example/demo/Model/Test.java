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
    @Column
    private int UserID;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int TestID;

    @Column
    private String DateTime;

    @Column
    private String URL;

    @Column
    private int NoOfUsers;

    @Column
    private int RampUpTime;

    @Column
    private int DurationTime;

    @Column
    private String ServerLocation;

    @Column
    private Double AverageResponseTime;

    @Column
    private Double SuccessRate;

    @Column
    private Double HTMLReportLink;

    public Test(int UserID, int TestID, String DateTime, String URL, int NoOfUsers, int RampUpTime, int DurationTime,
            String ServerLocation, Double AverageResponseTime, Double SuccessRate, Double HTMLReportLink) {
        super();
        this.UserID = UserID;
        this.TestID = TestID;
        this.DateTime = DateTime;
        this.URL = URL;
        this.NoOfUsers = NoOfUsers;
        this.RampUpTime = RampUpTime;
        this.DurationTime = DurationTime;
        this.ServerLocation = ServerLocation;
        this.AverageResponseTime = AverageResponseTime;
        this.SuccessRate = SuccessRate;
        this.HTMLReportLink = HTMLReportLink;
    }

    public Test() {
        super();
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getTestID() {
        return TestID;
    }

    public void setTestID(int testID) {
        TestID = testID;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String uRL) {
        URL = uRL;
    }

    public int getNoOfUsers() {
        return NoOfUsers;
    }

    public void setNoOfUsers(int noOfUsers) {
        NoOfUsers = noOfUsers;
    }

    public int getRampUpTime() {
        return RampUpTime;
    }

    public void setRampUpTime(int rampUpTime) {
        RampUpTime = rampUpTime;
    }

    public int getDurationTime() {
        return DurationTime;
    }

    public void setDurationTime(int durationTime) {
        DurationTime = durationTime;
    }

    public String getServerLocation() {
        return ServerLocation;
    }

    public void setServerLocation(String serverLocation) {
        ServerLocation = serverLocation;
    }

    public Double getAverageResponseTime() {
        return AverageResponseTime;
    }

    public void setAverageResponseTime(Double averageResponseTime) {
        AverageResponseTime = averageResponseTime;
    }

    public Double getSuccessRate() {
        return SuccessRate;
    }

    public void setSuccessRate(Double successRate) {
        SuccessRate = successRate;
    }

    public Double getHTMLReportLink() {
        return HTMLReportLink;
    }

    public void setHTMLReportLink(Double hTMLReportLink) {
        HTMLReportLink = hTMLReportLink;
    }

}