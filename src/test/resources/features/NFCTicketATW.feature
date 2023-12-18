@NFCTicket
Feature: Perform NFC transactions with Authenticate Tag Worker

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

    # login to ad hoc shift
    Given I am on the main page
    And I send the application to the background
    Then The day prep page should be opened
    When I click on login to shift button
    Then The driver shift login popup should be opened
    And I enter the "00002" driver ID
    And I enter the "28071" driver pin
    And I click on the login to shift button
    Then The adhoc shift pop up should be opened
    When I click on the continue to adhoc shift button
    Then I should be logged in to the shift successfully
    And The shift page should be opened

    # start an ad hoc shift trip successfully
    Given I am on the shift page
    When I click on the start trip button
    Then The trips page should be opened
    And I search for a "Sosh Live Route 1" route
    When I click on the route
    Then The select route direction pop up should be opened
    When I click on the route number to start trip
    Then The route "Sosh Live Route 1" should be started successfully
    Given I am on a route

  @Regression
    # perform a successful transaction using a blocked NFC card
  Scenario: Perform successful blocked, re-issued, and disabled NFC card transactions
    And I have a blocked NFC card
    When I tap NFC card on the left NFC reader
    Then The "Card is blocked" message should be displayed

      # perform a successful card is re-issued transaction
    And I have a re-issued from NFC card
    When I tap NFC card on the left NFC reader
    Then The "Card is reissued" message should be displayed

      # perform a successful transaction using a disabled NFC card
    And I have a disabled NFC card
    When I tap NFC card on the left NFC reader
    Then The "Card is disabled" message should be displayed