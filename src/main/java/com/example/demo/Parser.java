package com.example.demo;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class Parser {
    private Document doc;

    public Parser(String filePath) {
        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }










        
    }
     // Parse threads, ramp time, duration from JMX file
    public void parseThreadGroup() {
        try {
            NodeList threadGroupNodeList = doc.getElementsByTagName("ThreadGroup");
            if (threadGroupNodeList.getLength() > 0) {
                Element threadGroupElement = (Element) threadGroupNodeList.item(0);
                int numThreads = Integer.parseInt(threadGroupElement.getElementsByTagName("intProp").item(0).getTextContent());
                int rampTime = Integer.parseInt(threadGroupElement.getElementsByTagName("intProp").item(1).getTextContent());
                long duration = Long.parseLong(threadGroupElement.getElementsByTagName("longProp").item(0).getTextContent());

                System.out.println("Number of Threads: " + numThreads);
                System.out.println("Ramp Time: " + rampTime);
                System.out.println("Duration: " + duration);
            } else {
                System.err.println("ThreadGroup element not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Parse domain (URL) from .JMX
    public void parseHTTPSamplerProxy() {
        try {
            NodeList httpSamplerNodeList = doc.getElementsByTagName("HTTPSamplerProxy");
            if (httpSamplerNodeList.getLength() > 0) {
                Element httpSamplerElement = (Element) httpSamplerNodeList.item(0);
                String domain = httpSamplerElement.getElementsByTagName("stringProp").item(0).getTextContent();

                System.out.println("Domain: " + domain);
            } else {
                System.err.println("HTTPSamplerProxy element not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // You can add more methods to parse other parts of the XML or CSV files

    public static void main(String[] args) {
        Parser parser = new Parser("nba.jmx"); // Is there only 1 JMX? I am going to just use the NBA one
        parser.parseThreadGroup();
        parser.parseHTTPSamplerProxy();
    }
}