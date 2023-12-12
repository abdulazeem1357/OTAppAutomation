Feature: Login to the terminal

  Background:
     # visit the settings page
    Given I launch the application
    Then The main page should be opened
    When I click on the side menu button
    Then The side menu should be opened
    And I click on the settings page button
    Then The admin dialogue should be opened
    And I enter the pin to access the settings page
    And I click on the verify pin button
    Then The settings page should be opened

    # navigate to the terminal settings page
    Given I am on the settings page
    When I click on the terminal settings page button
    Then The terminal settings page should be opened

     # turn off the auto disable wifi
    When I click on the auto disable wifi button
    Then The auto disable wifi dialogue should be opened
    And I click on the no button on the auto disable wifi dialogue
    Then The auto disable wifi should be set to "No"

    Then The terminal settings page should be opened
    When I navigate to the settings page
    Then The settings page should be opened

    # login to the terminal successfully
  Scenario: Login to the Terminal successfully
    Given I am on the settings page
    When I click on the login page button
    Then The login page should be opened
    When I click on the User ID button
    And I give the terminal "zaf0691" User ID
    Then I click ok to accept the username
    When I click on the password button
    And I give the terminal "zaf0691" password
    Then I click ok to accept the password
    When I click on the terminal server button
    And I select the "Putco Stg" environment
    When I click on the login to terminal button
    Then I should see the message "You have been logged in to Open Transit" after attempting to login to the terminal
    When I click ok to close the login to terminal dialogue
    Then The login page should be opened

  # login failed Terminal Server is not valid when no Terminal Server is selected
  Scenario: Login fails when no Terminal Server is selected
    Given I am on the settings page
    When I click on the login page button
    Then The login page should be opened
    When I click on the User ID button
    And I give the terminal "zaf0691" User ID
    Then I click ok to accept the username
    When I click on the password button
    And I give the terminal "zaf0691" password
    Then I click ok to accept the password
    When I click on the login to terminal button
    Then I should see the message "Terminal server selection is not valid" after attempting to login to the terminal
    When I click ok to close the login to terminal dialogue
    Then The login page should be opened

    @Smoke
  # login failed due to no network connectivity
  Scenario: Login fails due to no network connectivity
    Given I am on the settings page
    When I click on the login page button
    Then The login page should be opened
    And I turn off the wifi connectivity
    When I click on the login to terminal button
    Then I should see the message "There is no active connection. Please make sure you are connected to the internet." after attempting to login to the terminal
    When I click ok to close the login to terminal dialogue
    Then The login page should be opened

    # This scenario outline covers various login possibilities
    # and their outcomes based on different combinations of User ID, password, and terminal environment.
    # It checks the appropriate feedback messages for various invalid and valid inputs.
  Scenario Outline: Validate terminal login with various User ID, Password, and Terminal Environment combinations
    Given I am on the settings page
    When I click on the login page button
    Then The login page should be opened
    When I click on the User ID button
    And I give the terminal "<terminalUserID>" User ID
    Then I click ok to accept the username
    When I click on the password button
    And I give the terminal "<terminalPassword>" password
    Then I click ok to accept the password
    When I click on the terminal server button
    And I select the "<terminalEnvironment>" environment
    When I click on the login to terminal button
    Then I should see the message "<terminalLoginMessage>" after attempting to login to the terminal
    When I click ok to close the login to terminal dialogue
    Then The login page should be opened

    Examples:
      | terminalUserID | terminalPassword | terminalEnvironment | terminalLoginMessage      |
      |                | zaf0691          | Putco Stg           | User id is not valid      |
      | zaf0691        |                  | Putco Stg           | Password is not valid     |
      | pak0030        | zaf0691          | Putco Stg           | Invalid Terminal Password |
      | zaf0691        | pak0030          | Putco Stg           | Invalid Terminal Password |
      | zaf0691        | zaf0691          | Putco QA            | Invalid IMEI/MAC provided |