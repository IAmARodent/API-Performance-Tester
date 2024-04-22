package com.example.demo;

import org.w3c.dom.*;

import com.example.demo.Model.TestResults;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.lang.Math;

public class Parser {
    private Document doc;

    public Parser(String xmlFilePath, String csvFilePath) {
        try {
            if (xmlFilePath != null) {
                parseXML(xmlFilePath);
            }

            if (csvFilePath != null) {
                parseCSV(csvFilePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseXML(String xmlFilePath) throws Exception {
        File xmlFile = new File(xmlFilePath);
        DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder xmlBuilder = xmlFactory.newDocumentBuilder();
        doc = xmlBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
    }

    public CSVProperties parseCSV(String csvFilePath) {

        int totalResponseTime = 0;
        int successCount = 0;
        int successRate;
        String humanReadableDate = null;

        try {
            File csvFile = new File(csvFilePath);
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line;
            int lineCount = 0; 
            boolean skipHeader = true; // Skip the header line
            boolean dateObtained = false; // get only first line of timestamp

            while ((line = br.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue; 
                }
                String[] data = line.split(","); 
                if (!dateObtained) {
                    long epochTimeSeconds = Long.parseLong(data[0]); 
                    humanReadableDate = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (epochTimeSeconds));
                    dateObtained = true;
                }
                long responseTime = Long.parseLong(data[1]);
                totalResponseTime += responseTime;
                if (data[7].equals("true")) {
                    successCount++;
                } 
                lineCount++;
            }
            br.close();
            int testedLines = lineCount;
            double average = (double) totalResponseTime / testedLines;
            successRate = (int)((double) (successCount / testedLines) * 100);
            System.out.println(successCount + " " +testedLines);
            return new CSVProperties(average, successRate, humanReadableDate);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static class CSVProperties {
        double avgResponseTime = 0;
        int successRate = 0;
        String humanReadableDate;

        public CSVProperties(double avgResponseTime, int successRate, String humanReadableDate) {
            this.avgResponseTime = avgResponseTime;
            this.successRate = successRate;
            this.humanReadableDate = humanReadableDate;
        }

        public double getAvgResponseTime() {
            return Math.round(avgResponseTime * 100) / 100.0;
        }

        public int getSuccessRate() {
            return successRate;
        }

        public String getDate() {
            return humanReadableDate;
        }
    }

    public static class JMXProperties {
        private int numThreads;
        private int rampTime;
        private long duration;
        private String domain;
        private String path;

        public JMXProperties(int numThreads, int rampTime, long duration, String domain, String path) {
            this.numThreads = numThreads;
            this.rampTime = rampTime;
            this.duration = duration;
            this.domain = domain;
            this.path = path;
        }

        public void editJMXFile(String filePath, int newNumThreads, int newRampTime, 
                                long newDuration, String newDomain, String newPath) {
            try {
                File file = new File(filePath);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(file);
    
                NodeList threadGroupNodeList = doc.getElementsByTagName("ThreadGroup");
                if (threadGroupNodeList.getLength() > 0) {
                    Element threadGroupElement = (Element) threadGroupNodeList.item(0);
                    NodeList intProps = threadGroupElement.getElementsByTagName("intProp");
                    NodeList longProps = threadGroupElement.getElementsByTagName("longProp");
    
                    // Update numThreads
                    if (intProps.getLength() > 0) {
                        Element numThreadsElement = (Element) intProps.item(0);
                        numThreadsElement.setTextContent(String.valueOf(newNumThreads));
                        numThreads = newNumThreads;
                    }
    
                    // Update rampTime
                    if (intProps.getLength() > 1) {
                        Element rampTimeElement = (Element) intProps.item(1);
                        rampTimeElement.setTextContent(String.valueOf(newRampTime));
                        rampTime = newRampTime;
                    }
    
                    // Update duration
                    if (longProps.getLength() > 0) {
                        Element durationElement = (Element) longProps.item(0);
                        durationElement.setTextContent(String.valueOf(newDuration));
                        duration = newDuration;
                    }
                } else {
                    System.err.println("ThreadGroup element not found.");
                }
    
                    // Update domain property
                    NodeList httpSamplerNodeList = doc.getElementsByTagName("HTTPSamplerProxy");
                    if (httpSamplerNodeList.getLength() > 0) {
                        Element httpSamplerElement = (Element) httpSamplerNodeList.item(0);
                        NodeList stringProps = httpSamplerElement.getElementsByTagName("stringProp");
                        for (int i = 0; i < stringProps.getLength(); i++) {
                            Element stringPropElement = (Element) stringProps.item(i);
                            String propName = stringPropElement.getAttribute("name");
                            if ("HTTPSampler.domain".equals(propName)) {
                                stringPropElement.setTextContent(newDomain);
                                domain = newDomain;
                            } 
                            else if ("HTTPSampler.path".equals(propName)) {
                                stringPropElement.setTextContent(newPath);
                                path = newPath;
                            } 
                        }
                    } else {
                        System.err.println("HTTPSamplerProxy element not found.");
                    }
                    // Write the updated document back to the file
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(file);
                    transformer.transform(source, result);
    
                    System.out.println("JMX file updated successfully.");
    
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error editing JMX file: " + e.getMessage());
            }
        }

        // Getters
        public int getNumThreads() {
            return numThreads;
        }

        public int getRampTime() {
            return rampTime;
        }

        public long getDuration() {
            return duration;
        }

        public String getDomain() {
            return domain;
        }

        public String getPath() {
            return path;
        }
    }    

    // Parse threads, ramp time, duration from JMX file
    public JMXProperties parseJMXProperties() {
        int numThreads;
        int rampTime;
        long duration;
        String domain; 
        String path;
        try {
            NodeList threadGroupNodeList = doc.getElementsByTagName("ThreadGroup");
            if (threadGroupNodeList.getLength() > 0) {
                Element threadGroupElement = (Element) threadGroupNodeList.item(0);
                numThreads = Integer.parseInt(threadGroupElement.getElementsByTagName("intProp").item(0).getTextContent());
                rampTime = Integer.parseInt(threadGroupElement.getElementsByTagName("intProp").item(1).getTextContent());
                duration = Long.parseLong(threadGroupElement.getElementsByTagName("longProp").item(0).getTextContent());

            } else {
                System.err.println("ThreadGroup element not found.");
                return null;
            }

            NodeList httpSamplerNodeList = doc.getElementsByTagName("HTTPSamplerProxy");
            if (httpSamplerNodeList.getLength() > 0) {
                Element httpSamplerElement = (Element) httpSamplerNodeList.item(0);
                domain = httpSamplerElement.getElementsByTagName("stringProp").item(0).getTextContent();
                path = httpSamplerElement.getElementsByTagName("stringProp").item(1).getTextContent();
            } else {
                System.err.println("HTTPSamplerProxy element not found.");
                return null;
            }

        return new JMXProperties(numThreads, rampTime, duration, domain, path); // add path here as new param

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Parser p = new Parser("nba.jmx", "results.csv"); 
        
        JMXProperties j = p.parseJMXProperties();
        System.out.println("INITIAL .JMX INFORMATION");
        System.out.println("Duration: " + j.getDuration());
        System.out.println("Thread Num: " + j.getNumThreads());
        System.out.println("Ramp Time: " + j.getRampTime());
        System.out.println("Domain: " + j.getDomain());
        System.out.println("Path: " + j.getPath()); 

        System.out.println("----------------------------------------------------");
    

        j.editJMXFile("nba.jmx", 200, 200, 200, "www.EDit.com", "/PAAAAAAAAAAAAATH"); // uncomment this to edit

        System.out.println("NEW STUFF: ");
        System.out.println("Duration: " + j.getDuration());
        System.out.println("Thread Num: " + j.getNumThreads());
        System.out.println("Ramp Time: " + j.getRampTime());
        System.out.println("Domain: " + j.getDomain());
        System.out.println("Path: " + j.getPath()); 

        CSVProperties c = p.parseCSV("results.csv");
        System.out.println("Avg Response Time: " + c.getAvgResponseTime());
        System.out.println("Success Rate: " + c.getSuccessRate());
        System.out.println("Date (of first test): " + c.getDate());
        

        /*  
         *  import com.example.demo.Parser.CSVProperties;
            import com.example.demo.Parser.JMXProperties;
         */

    }

    
    public static void something(int users, int ramptime, int duration, String url, String path)
    {
        Parser p = new Parser("nba.jmx", "results.csv"); 
        JMXProperties j = p.parseJMXProperties();
        j.editJMXFile("nba.jmx", users, ramptime, duration, url, path);
    }
    public static TestResults something2(String link)
    {
        Parser p = new Parser("nba.jmx", "results.csv"); 
        CSVProperties c = p.parseCSV("results.csv");
        TestResults results = new TestResults();
        results.setResponseTime(c.getAvgResponseTime());
        results.setSuccessRate(c.getSuccessRate());
        results.setDate(c.getDate());
        results.setHtmlreportlink("https://api-load-tester-html-reports.nyc3.digitaloceanspaces.com/" + link + "/index.html");        
        return results;
    }

}