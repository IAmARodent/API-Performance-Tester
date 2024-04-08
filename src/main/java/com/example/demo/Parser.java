package com.example.demo;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

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

        try {
            File csvFile = new File(csvFilePath);
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line;
            int lineCount = 0; 
            boolean skipHeader = true; // Skip the header line

            while ((line = br.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue; 
                }

                 // Read every third line
                if (lineCount % 3 == 0) { 
                    String[] data = line.split(","); 
                    long responseTime = Long.parseLong(data[1]);
                    totalResponseTime += responseTime;
                    if (data[7].equals("true")) {
                        successCount++;
                    } 
                    lineCount++;
                }
                lineCount++;
            }
            br.close();

            int testedLines = lineCount / 3;
            double average = (double) totalResponseTime / testedLines;
            successRate = (successCount / testedLines) * 100;

            return new CSVProperties(average, successRate);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class CSVProperties {
        double avgResponseTime = 0;
        int successRate = 0;

        public CSVProperties(double avgResponseTime, int successRate) {
            this.avgResponseTime = avgResponseTime;
            this.successRate = successRate;
        }

        public double getAvgResponseTime() {
            return avgResponseTime;
        }

        public int getSuccessRate() {
            return successRate;
        }

    }

    // inner class to retrieve JMX properties
    public static class JMXProperties {
        private int numThreads;
        private int rampTime;
        private long duration;

        // Constructor
        public JMXProperties(int numThreads, int rampTime, long duration) {
            this.numThreads = numThreads;
            this.rampTime = rampTime;
            this.duration = duration;
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
    }    

    // Parse threads, ramp time, duration from JMX file
    public JMXProperties parseJMXProperties() {
        try {
            NodeList threadGroupNodeList = doc.getElementsByTagName("ThreadGroup");
            if (threadGroupNodeList.getLength() > 0) {
                Element threadGroupElement = (Element) threadGroupNodeList.item(0);
                int numThreads = Integer.parseInt(threadGroupElement.getElementsByTagName("intProp").item(0).getTextContent());
                int rampTime = Integer.parseInt(threadGroupElement.getElementsByTagName("intProp").item(1).getTextContent());
                long duration = Long.parseLong(threadGroupElement.getElementsByTagName("longProp").item(0).getTextContent());

                return new JMXProperties(numThreads, rampTime, duration);
            } else {
                System.err.println("ThreadGroup element not found.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getPropertyFromHttpSampler(String propertyName) {
        try {
            NodeList httpSamplerNodeList = doc.getElementsByTagName("HTTPSamplerProxy");
            if (httpSamplerNodeList.getLength() > 0) {
                Element httpSamplerElement = (Element) httpSamplerNodeList.item(0);
                if ("testname".equals(propertyName)) {
                    return httpSamplerElement.getAttribute(propertyName);
                } else {
                    return httpSamplerElement.getElementsByTagName("stringProp").item(0).getTextContent();
                }
            } else {
                System.err.println("HTTPSamplerProxy element not found.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public String parseDomain() {
        return getPropertyFromHttpSampler("HTTPSampler.domain");
    }
    
    public String parseTestName() {
        return getPropertyFromHttpSampler("testname");
    }

    public static void main(String[] args) {
        Parser p = new Parser("nba.jmx", "results.csv"); 
        JMXProperties j = p.parseJMXProperties();
        CSVProperties c = p.parseCSV("results.csv");
        System.out.println("Duration: " + j.getDuration());
        System.out.println("Thread Num: " + j.getNumThreads());
        System.out.println("Ramp Time: " + j.getRampTime());
        System.out.println("Domain: " + p.parseDomain());
        System.out.println("Avg Response Time: " + c.getAvgResponseTime());
        System.out.println("Test Name: " + p.parseTestName());
        System.out.println("Success Rate: " + c.getSuccessRate());


        /*
         *  import com.example.demo.Parser.CSVProperties;
            import com.example.demo.Parser.JMXProperties;
         */
    }
}