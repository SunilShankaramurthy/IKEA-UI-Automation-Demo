# IKEA-UI-Automation-Demo

## Table of Content
- [Description](#description)
- [Architecture](#architecture)
- [PreRequisite](#prerequisite)
- [Run-Command](#run-command)
- [Report](#report)
- [Future-Enhancements](#future-enhancements)

## Description
   This is POC project on mobile application testing for IKEA android app to test the UI functionality in TDD format. 
   This repo contains all the basic facilities of a production ready framework like Parameterization, test retries, customizable detailed report.
## Architecture
  This is a Maven project developed using Java Appium and TestNG.<br/>
    - Maven take cares of dependency jars and plugins.<br/>
    - Configured to use external property file and user inputs which allows easy execution with different input data as well as different test types.<br/> 
    - TestNG will facilitate executing tests with dynamic data.<br/>
    - Implemented Page Object Model and pagefactory for locate elements and page navigation.<br/>
    - Maintained device and app configurations in devices.json and config.properties files respectively.<br/>
    - Provides access to produce user defined Test Reports with screenshot for errored test scenarios.<br/> 
    
    ** Framework is configured in such a way that, Appium server will start programatically when you run the suite using maven command which will even take     care of stopping the server 
    after the test completion. This avoids the manual intervention during multiple runs.
    However, we need to make sure that default port (4723) used by appium is available.
    To change the port, you need to start the server manually by using this command: appium -p <port>.
    
  
## PreRequisite
 - Java 8
 - Maven
 - Appium 1.22.3
 - Android Studio
 - Hard disc space > 10GB
  
  
## Run-Command

   Start the AVD from Android studio and type the command in the terminal
   
    ** mvn clean install -Dtestngfile={testng} 
   
   If you want to retry the failed tests immediately then please use the below command (bydefault maxRetryCount=0)
   
      ** mvn clean install -Dtestngfile={testng} -DmaxRetryCount=2
      
## Report
   The framework will generate 2 test reports<br/>
   1. TestNG ExtentReport <br/>
      Path : project.dir/Extent.html<br/>
      <br/>
   2. TestNG default Report <br/>
      path : project.dir/target/surefire-reports/index.html
      
## Future-Enhancements
   - This framework is implemented to support parallel run but minor code updates are required.
   - Add capability to run failed scenarios alone when required.
         
