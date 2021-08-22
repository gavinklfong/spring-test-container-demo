# Integration Test Demo For Spring Boot 

## Overview
The purpose of this repository is to demonstrate the implementation of a fully automated integration test using Cucumber + Testcontainers.

The sample application is a message driven insurance claim review process.

[Application Component Diagram](https://raw.githubusercontent.com/gavinklfong/spring-test-container-demo/master/blob/Sample_App.png)

## Build

Run this command to build and run all automated tests

`mvn clean install`

If you would like to run the integration test only, then run this command

`mvn -Dtest=CucumberTest test`

## Integration Test Initialization

Prior to the test execution, Testcontainers are to be initialized and started. Next, the hostname and port of the containers are injected into the Spring Boot application context so that the application can be started with connectivity to the containers. Finally, run the test scenarios based on cucumber feature files.

The diagram below depicts the initialization process using classes which are highlighted in pink.
[Integration Test Initialization](https://raw.githubusercontent.com/gavinklfong/spring-test-container-demo/master/blob/Integration_Test_Init_Flow.png)

## Integration Test Design

There is a layer to abstract away operations of MongoDB and RabbitMQ in order to make test steps simple and easy to understand.

For data sharing among steps, we keep data in test data context. For example, step 3 is able to obtain the claim review update from test data context and verify the field values as the listener puts the data into the context once the application has submitted it to the topic exchange.

[Integration Test Design](https://raw.githubusercontent.com/gavinklfong/spring-test-container-demo/master/blob/Integration_Test_Run.png)
