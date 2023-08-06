Feature: Login to the terminal

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
  Scenario Outline: Login to the terminal successfully
    Given I am on the settings page
    When I click on the login page button
    Then The login page should be opened
    And I turn on the wifi connectivity
    When I click on the User ID button
    And I give the terminal "<terminalUserID>" User ID
    Then I click ok to accept the username
    When I click on the password button
    And I give the terminal "<terminalPassword>" password
    Then I click ok to accept the password
    When I click on the terminal server button
    And I select the "<terminalEnvironment>" environment
    When I click on the login to terminal button
    Then I should be logged in to the terminal successfully
    When I click ok to close the login to terminal dialogue
    Then The login page should be opened

    Examples:
      | terminalUserID | terminalPassword | terminalEnvironment |
      | pak0039        | pak0039          | Putco Stg           |