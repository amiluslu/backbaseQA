# Getting Started

### Reference Documentation
This application based on JAVA 11, Selenium, TestNG, RestAssured with Maven, ExtentReport for reporting...
Aim of this project, Backbase QA Assignment challenge testing for Web and API of given application.

In Order To Run this project

    1) git pull.
    2) Run Scenarios.xml file in this project.  
    3) After execution is done, you can see the detailed report of automated scenarios.
    4) If scenarios are failed, you can see the screenshot of scenarios that failed steps.


Explanation of Key Points:

    * TestReport Folder -->
        There is a HTML file (Test-Automation-Report.html) that reports the results of automated scenarios.
            
    * Screenshot Folder -->
        Takes the screenshot of failed automation test scenarios.
        
    * Specifications -->
        Page Object Model, Pojos and Maven architecture is applied in that project. 
        Apitesting package contains RestAssured API tests.
        Cases package contains Selenium WEB tests.
        config.properties file contains parameters also for headless or not.
        TestNG --> webtests.xml contains selenium scenarios.
        TestNG --> apitests.xml contains RestAssured-API scenarios.
        Jenkinsfile also contains for pipeline configuration. 

