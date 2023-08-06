Feature: Day preparation checks the application health. Download, and upload necessary files on the device.

  #launch the application and perform successful login
  Background:
    # set the gps to if available
    Given I launch the application
    Then The main page should be opened
    When I click on the side menu button
    Then The side menu should be opened
    And I click on the settings page button
    Then The admin dialogue should be opened
    And I enter the pin to access the settings page
    And I click on the verify pin button
    Then The settings page should be opened
    When I click on the hardware settings page
    Then The hardware settings page should be opened
    When I click on the gps button
    Then The use GPS for location dialogue should be opened
    When I click on to set the GPS to if available button
    Then The GPS should be set to if available
    And The hardware settings page should be opened
    When I navigate to the settings page
    Then The settings page should be opened

    # login to the terminal successfully
    Given I am on the settings page
    When I click on the login page button
    Then The login page should be opened
    And I turn on the wifi connectivity
    When I click on the User ID button
    And I give the terminal "pak0039" User ID
    Then I click ok to accept the username
    When I click on the password button
    And I give the terminal "pak0039" password
    Then I click ok to accept the password
    When I click on the terminal server button
    And I select the "Putco Stg" environment
    When I click on the login to terminal button
    Then I should be logged in to the terminal successfully
    When I click ok to close the login to terminal dialogue
    Then The login page should be opened

    # navigate to the mainPage
    When I navigate to the settings page
    Then The settings page should be opened
    When I navigate to the main page
    Then The main page should be opened

    # downloading the data successfully
    Given I am already logged in
    And I am on the main page
    When I click on the side menu button
    Then The side menu should be opened
    When I click on the download data page button
    Then The download data page should be opened
    When I click on the start download button
    Then The download should be started
    And The data should be downloaded successfully
    When I click ok to accept the data download dialogue
    Then The download data page should be opened
    When I navigate to the main page from download page

    # run a successful day end check from the side menu after downloading data
  Scenario: Run day prep activity from side menu with data downloaded
    Given I am on the main page
    When I click on the side menu button
    Then The side menu should be opened
    And I click on the day end button
    Then The day prep page should be opened
#    And The connectivity check should be started
#    And The connectivity check should be in progress
    And The connectivity check should be passed
#    And The firmware check should be started
#    And The firmware check should be in progress
    And The firmware check should be passed
    And The configuration check should be passed
    And The download data check should be passed
    And The data reconciliation check should be passed
    And The data purge check should be passed
    And The logs sync check should be passed
    And The commands sync check should be passed
    And The notification sync check should be passed
#    And The hardware validation check should be passed
    When The day prep checks should be completed successfully
    Then The main page should be opened

  Scenario: Run day prep activity on relaunch of the application
    Given I am on the main page
    When I send the application to the background
    And I turn on the wifi connectivity
    Then The application should come to the foreground on "Day Start Checks" screen
    Then The day prep page should be opened
    And The day prep no internet connectivity dialogue box is displayed
    When I click on the continue button on the no internet day prep connectivity dialogue box
    Then I click on the day prep retry button
    And The connectivity check should be passed
    And The firmware check should be passed
    And The configuration check should be passed
    And The download data check should be passed
    And The data reconciliation check should be passed
    And The data purge check should be passed
    And The logs sync check should be passed
    And The commands sync check should be passed
    And The notification sync check should be passed
#    And The hardware validation check should be passed
    When The day prep checks should be completed successfully
    Then The main page should be opened