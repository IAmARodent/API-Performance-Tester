# Introduction
This application is a Web-based API Load Tester. It utilizes Java Spring as the backend and uses technologies such as DigitalOcean Spaces, SQL, and JMeter.
Currently this application is running on http://64.23.222.181:8080 which is the American server responsible for running American server tests as well as European and Asian servers already configured in the application.
If needed to replicate this application as intended, you must have three servers to run this application likely in America, Europe, and Asia.
# Prerequesites to install on machine/server:
1. Java Version 17
2. MySQL (only needed for the server hosting website)
# Steps to run:
1. Clone this repository to all your servers.
2. Choose a server that will host the website and setup up MySQL and create a database called apitester and update credentials in application.properties in src/main/resources.
3. Change lines 111, 122, 134 in usertesting.html in src/main/resources/templates to your servers' IP in America, Europe, and Asia. (only the server hosting the website) 
4. Run command "./mvnw spring-boot:run" in all servers.
5. Open up the website with the IP of the server that is hosting the website on port 8080.
6. The website will open.
# How to use application:
1. Create an account.
2. Log In.
3. Click Test API to be redirected to API testing page.
4. Input all parameters asked for. (path is not required only if apart of API url you are testing)
5. Click Run Test and wait until it redirects you to the dashboard. (this may take a few minutes depending on inputted duration and ramp-up time as well as making necessary html reports public in cloud storage)
6. In the dashbaord page you can see the results and a link to the HTML report that has been upload to DigitalOcean Spaces.
