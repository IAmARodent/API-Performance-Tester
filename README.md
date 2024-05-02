This application is a Web-based API Load Tester. It utilizes Java Spring as the backend and uses technologies such as DigitalOcean Spaces, SQL, and JMeter.

Currently this application is running on http://64.23.222.181:8080 which is the American server responsible for running American server tests as well as European and Asian servers already configured in the application.

If needed to replicate this application as intended, you must have three servers to run this application likely in America, Europe, and Asia.

Prerequesites to install on machine/server:
1. Java Version 17
2. MySQL (only needed for the server hosting website)

Steps to run:
1. Clone this repository to all your servers.
2. Choose a server that will host the website and setup up MySQL and credentials in application.properties in src/main/resources.
3. Change lines 111, 122, 134 in usertesting.html in src/main/resources/templates to your servers' IP in America, Europe, and Asia. (only the server hosting the website) 
4. Run command "./mvnw spring-boot:run" in all servers.
5. Open up the website with the IP of the server that is hosting the website on port 8080.
6. The website will open.
