# Maven Project: Selenium along with TestNG --> UI testing

## Pre-requirements for project setup

- IDE (IntelliJ)
- Maven
- Chrome web-driver downloaded to src/test/resources/drivers
- openjdk version "1.8.0_222" at least
- application to test --> https://github.com/magemello/adf-automation-qa

## Description 
###Describes of how to run the automation test
- Download ZIP file with the repository or copy the HTTPS link and (the recommended choice, the last one)
- Open your IDE and click on "Import from version control"
- Once the repository is on the local machine, run "Build Project" in order to make sure everything is ok
- To start running tests, one must configure a TestNG "Run configuration" like below:
        VM options --> "-ea -Dtestng.dtd.http=true"
        Test kind --> Suite
        Suite --> /Users/"yourUser"/IdeaProjects/Ness-Alfresco-UI-Tests/src/test/java/alfresco/testNGsuites/allTests.xml
- Run tests by clicking on the IDE's Run button, along with the above configuration

## Explanation 
###Describes the choice made in the solution
- Page object model (POM) is chosen as a design for this repository
- POM file contains all the dependencies necessary to run tests
- allTests.xml contains a configuration to run tests by class name
- multiple .xml files can be configured to run tests, depending on each suite containing multiples tests or not