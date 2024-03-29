package com.example.demo;

//class code referenced from https://www.blazemeter.com/blog/jmeter-command-line
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import java.io.File;

public class JMeter {

    public String poop()
    {
        return "Success";
    }
    public String runTest() throws Exception {
        try{
            // JMeter Engine
            StandardJMeterEngine jmeter = new StandardJMeterEngine();


            // Initialize Properties, logging, locale, etc.
            JMeterUtils.loadJMeterProperties("../apache-jmeter-5.6.3/bin/jmeter.properties");
            JMeterUtils.setJMeterHome("../apache-jmeter-5.6.3");
            //JMeterUtils.initLogging();// you can comment this line out to see extra log messages of i.e. DEBUG level
            JMeterUtils.initLocale();

            // Initialize JMeter SaveService
            SaveService.loadProperties();

            // Load existing .jmx Test Plan
            File in = new File("nba.jmx");
            HashTree testPlanTree = SaveService.loadTree(in);
            
            String csvResults = "results.csv";
            ResultCollector result = new ResultCollector();
            result.setFilename(csvResults);
            testPlanTree.add(testPlanTree.getArray()[0], result);

            // Run JMeter Test
            jmeter.configure(testPlanTree);
            jmeter.run();
            return "hi";
        }
        catch (Exception e) {
            // TODO: handle exception
            return "haha error";
        }
    }
}
