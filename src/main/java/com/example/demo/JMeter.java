package com.example.demo;

//class code referenced from https://www.blazemeter.com/blog/jmeter-command-line
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import java.io.FileInputStream;

public class JMeter {

    public String poop()
    {
        return "Success";
    }
    public static void main(String[] argv) throws Exception {
        /** 
        // JMeter Engine
        StandardJMeterEngine jmeter = new StandardJMeterEngine();


        // Initialize Properties, logging, locale, etc.
        JMeterUtils.loadJMeterProperties("/path/to/your/jmeter/bin/jmeter.properties");
        JMeterUtils.setJMeterHome("/path/to/your/jmeter");
        //JMeterUtils.initLogging();// you can comment this line out to see extra log messages of i.e. DEBUG level
        JMeterUtils.initLocale();

        // Initialize JMeter SaveService
        SaveService.loadProperties();

        // Load existing .jmx Test Plan
        FileInputStream in = new FileInputStream("/path/to/your/jmeter/extras/Test.jmx");
        HashTree testPlanTree = SaveService.loadTree(in);
        in.close();

        // Run JMeter Test
        jmeter.configure(testPlanTree);
        jmeter.run();
        */
    }
}
