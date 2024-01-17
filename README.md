# Project Setup Guide: How to Install and Configure the Mass Transit for Testing
## Introduction
This documentation serves as a comprehensive guide for understanding and contributing to the test automation of the Mass Transit application.

The Mass Transit test automation project is focused on ensuring the quality of the application through automated testing. The test automation framework utilizes industry-standard tools and techniques to ensure comprehensive testing of the application.

This documentation provides a step-by-step guide for setting up the Mass Transit test automation environment, as well as an overview of the various testing components and methodologies used in the framework.

It uses AppiumDriver and TestNG to create and execute automated tests. The cucumber framework is used to provide a scalable and maintainable solution for automated testing, which can be extended to include new test scenarios with ease.

Whether you are a new user looking to get started with the Mass Transit application, or an experienced test engineer looking to extend or contribute to it, this documentation should provide all the necessary information and resources to help you achieve your goals.

The following sections will cover the installation and setup process, as well as provide an overview of the main features and functionalities of the Mass Transit application.

## Requirements
The following requirements are necessary to set up and run the test automation project for the Mass Transit application.

### Operating System
The test automation project can be set up on any operating system that supports Java and Maven.

### Android Device
The Mass Transit application is available only on Android devices running on v7.1.1 of Android.

### Tools
Appium is used for automating tests on the Mass Transit application.
IntelliJ Community Edition is the recommended Integrated Development Environment (IDE) for developing and running the automation project.
Maven is used as the build automation tool for the automation project.

### Programming Language
Both the automation project and the Mass Transit application are built on Java programming language.

### Testing framework
The test automation project uses the TestNG and Cucumber testing framework for test management and reporting.

### Dependencies
The test automation project requires the installation of the necessary dependencies such as the Appium client library, TestNG, Cucumber, Allure, Apache, and Selenium.

#### Appium
It is an open-source test automation framework used for automating mobile applications on various platforms such as Android and iOS. It uses the WebDriver protocol to communicate with mobile devices.

#### TestNG
It is a testing framework for the Java programming language that is used to perform unit, functional, integration, and end-to-end testing of software applications. It provides advanced features such as annotations, test grouping, and parallel execution.

#### Cucumber
It is a testing tool that supports Behavior-Driven Development (BDD) methodology. It allows the creation of test cases in a human-readable language and enables collaboration between developers, testers, and stakeholders.

#### Allure
It is a reporting tool used for generating reports for automated tests. It provides detailed information on test results, test steps, and screenshots, making it easier for the team to identify issues and track progress.

#### Apache
It is an open-source software foundation that provides a wide range of tools and frameworks for software development. It is known for its popular web server, Apache HTTP Server, but also offers other tools such as Maven, which is a build automation tool used to manage dependencies and build projects.

#### Selenium
It is an open-source test automation tool used to automate web browsers. It provides a suite of tools for writing automated tests in multiple programming languages such as Java, Python, and Ruby. It is widely used in the industry for testing web applications.

### Test data
The test automation project requires test data such as user credentials and test scenarios to be provided for effective testing.

## Installation
Follow the steps below to install and set up the test automation project for the Mass Transit application on your machine.

Here are the step-by-step instructions for installing the test automation project on a Windows operating system:
1. Download and install Java Development Kit (JDK) version 8 (v1.8) from Oracle's website.
2. Install IntelliJ Community Edition from the JetBrains website or using the JetBrains Toolbox App.
3. Clone the test automation project repository from the Git repository hosting service GitLab.
4. Install the Apache Maven build automation tool by downloading the binary files from the Apache website, extracting them to a folder, and adding the bin folder to the system path environment variable.
5. Install the Appium desktop application by downloading the latest version from the official Appium website installing it with the default settings and adding Appium to the environment variable.
6. Connect an Android device to the computer via a USB cable and enable USB debugging on the device.
7. Install Android SDK tools such as adb and add their paths to the system path environment variable or install the Android Studio which comes bundled with the Android SDK and manage all the SDK tools from the SDK manager in Android Studio.
8. Open the cloned project in IntelliJ and wait for the dependencies to be downloaded automatically.
9. Run the tests using the Cucumber BDD framework and generate the test reports using the Allure reporting tool.

By following these steps, the test automation project can be installed on a Windows-based operating system, and the development environment can be set up for the project.

## Configuration
The configuration process of the test automation project is designed to be streamlined and user-friendly. 

Once all the necessary tools and dependencies have been installed, you can simply open the IntelliJ IDE, clone the project's repository, and begin testing the Mass Transit application. Overall, the project's configuration has been optimized to prioritize productivity and ease of use for the user.

In our test automation project, we have stored the desired capabilities in an external configuration file using the Apache IO dependency. This dependency makes it easier for us to read from the configuration file in our Java code. Using an external configuration file makes it more organized and easier to maintain, as any changes to the desired capabilities can be made in the configuration file rather than in the Java code itself.

Additionally, we have created separate classes for logging and basic Android functionalities such as turning on/off networks on the Android device. This helps to improve the modularity and maintainability of our code. The logging class allows us to log information at different levels such as debug, info, and error, making it easier to debug our code when necessary. The Android functionality class allows us to perform common tasks that are required in our tests, such as turning on/off network connectivity.

Overall, by using the Apache IO dependency and creating separate classes for logging and Android functionalities, our test automation project is more organized, modular, and easier to maintain.

## Running the project
To run the test automation project using the Maven, follow the below steps:
1. Open the project in the IDE, and locate the Runner class that needs to be executed.
2. Make sure that the device is connected to the computer and that the device is running the desired version 7.1.1 of the Android os.
3. Next, start the Appium server by running the following command in the terminal:

```appium```


Once the server has started, navigate to the directory containing the runner class in the terminal.
Run the feature files by executing the following command:

```mvn clean test```

The test automation project will then run and generate the test reports in the specified format (Allure, TestNG, or Cucumber).

## HTML Report using Allure Combine
### Pre-requisite
1. Allure should be installed and configured on the system.
2. Allure-combine should be installed and configured on the system.

### Installing allure-combine
Run the pip install allure-combine command to install it.

```pip install allure-combine```

### Notes
Link to allure-combine repo - [Allure Combine Repository](https://github.com/MihanEntalpo/allure-single-html-file)

### Steps
1. The CMD should be opened at target folder directory where the allure-results folder is placed.
2. Run the allure generate command to create allure-report folder in the same directory.
3. Run the allure-combine ./allure-report command to create a complete HTML report which can be shared.

## Troubleshooting
Despite thorough testing and preparation, issues may still arise when setting up, configuring, or running the test automation project. To help address any issues that may arise, here is a list of common problems and their possible solutions:

### Installation issues
If there are any issues with installing the project dependencies or tools, double-check that all the required components are installed correctly and that the versions match the project requirements.

### Configuration issues
If there are any issues with configuring the project, make sure all the settings and parameters are correct and accurately entered. Double-check any external configuration files and make sure the correct paths are being referenced.

### Running the project issues
If there are issues with running the project, ensure that the correct commands and steps are being followed. Check for any errors in the console output or log files that may point to issues.

### Testing issues
If there are issues with running the tests, ensure that the correct test data is being used and that the tests are being executed correctly. Review the test reports and logs for any errors or failures, and take appropriate action to address them.

## Conclusion
In conclusion, this test automation project for the Mass Transit application is designed to help others easily understand and contribute to further test automation of the application. With the use of Appium, TestNG, Cucumber, Allure, Apache, and Selenium, the automation project is highly efficient and effective in automating the testing process. 

By following the installation, configuration, running, and troubleshooting instructions provided in this documentation, users can get up and running with the project in no time. As with any software project, we recommend keeping the project and its dependencies up-to-date to ensure maximum compatibility and reliability.
