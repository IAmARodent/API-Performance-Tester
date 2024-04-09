package com.example.demo;

//class code referenced from https://www.blazemeter.com/blog/jmeter-command-line
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.report.dashboard.ReportGenerator;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.File;

public class JMeter {

    public String poop()
    {
        return "Success";
    }
    public void deleteCSV()
    {
        File results = new File("results.csv");
        results.delete();
        try{
            FileUtils.deleteDirectory(new File("report-output"));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public String runTest() throws Exception {
        try{
            deleteCSV();
            // JMeter Engine
            StandardJMeterEngine jmeter = new StandardJMeterEngine();


            // Initialize Properties, logging, locale, etc.
            JMeterUtils.loadJMeterProperties("apache-jmeter-5.6.3/bin/jmeter.properties");
            JMeterUtils.setJMeterHome("apache-jmeter-5.6.3");
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

            JMeterUtils.setProperty("jmeter.reportgenerator.exporter.html.classname", "org.apache.jmeter.report.dashboard.HtmlTemplateExporter");
            JMeterUtils.setProperty("jmeter.reportgenerator.exporter.html.property.output_dir", "report-output");

            // Run JMeter Test
            jmeter.configure(testPlanTree);
            jmeter.run();
            ReportGenerator generator = new ReportGenerator("results.csv", null);
            generator.generate();
            return "hi";

        }
        catch (Exception e) {
            // TODO: handle exception
            return "haha errorz";
        }
    }
}
