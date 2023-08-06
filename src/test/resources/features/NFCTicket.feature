Feature: Perform NFC transactions

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

    # login to ad hoc shift
    Given I am on the main page
    When I click on login to shift button
    Then The driver shift login popup should be opened
    And I enter the "001" driver ID
    And I enter the "98942" driver pin
    And I click on the login to shift button
    Then The adhoc shift pop up should be opened
    When I click on the continue to adhoc shift button
    Then I should be logged in to the shift successfully
    And The shift page should be opened

    # start an ad hoc shift trip successfully
    Given I am on the shift page
    When I click on the start trip button
    Then The trips page should be opened
    And I search for a "St Francis Bay to Oyster Bay Turnoff" route
    When I click on the route
    Then The select route direction pop up should be opened
    When I click on the route number to start trip
    Then The route should be started successfully

    # perform a successful product has expired transaction using NFC card with an expired ticket
  Scenario: Perform successful expired NFC transaction
    Given I am on a route
    And I have an expired ticket on NFC card
    When I tap NFC card with expired ticket on the left NFC reader
    Then The ticket is expired message should be displayed

      # perform a successful transaction using NFC card with an valid ticket
  Scenario: Perform successful valid NFC transaction
    Given I am on a route
    And I have a valid ticket on NFC card
    When I tap NFC card with active ticket on the left NFC reader
    Then The ticket is valid message should be displayed

    @ignore
    # perform a successful transfer leg within time transaction using NFC card with an valid ticket
  Scenario: Perform a successful transfer leg within time transaction using NFC card with an valid ticket
    Given I am on a route
    And I have a valid ticket on NFC card with a transfer leg
    When I tap NFC card with active ticket on the left NFC reader
    And I navigate to the transfer station
#    And I navigate to the "Transfer" station
    When I tap NFC card with active ticket on the left NFC reader
    Then The ticket is valid message should be displayed

  @ignore
    # perform a successful transaction with multiple merchants tickets on the NFC card
  Scenario: Perform a successful transaction with multiple merchants tickets on the NFC card
    Given I am on a route
    And I have multiple valid merchant tickets on the NFC card
    When I tap NFC card with active ticket on the left NFC reader
    Then The ticket is valid message should be displayed

  @ignore
    # perform a successful transaction using a blocked NFC card
  Scenario: Perform successful blocked NFC card transaction
    Given I am on a route
    And I have a blocked NFC card
    When I tap the blocked NFC card on the left NFC reader
    Then The card is blocked message should be displayed

  @ignore
    # perform a successful transaction using a re-issued NFC card valid ticket
  Scenario: Perform successful valid NFC transaction using re-issued NFC card
    Given I am on a route
    And I have a re-issued NFC card
    When I tap the re-issued NFC card with a valid ticket on the left NFC reader
    Then The ticket is valid message should be displayed

  @ignore
    # perform a successful transaction using a re-issued NFC card with expired ticket
  Scenario: Perform a successful expired ticket NFC transaction using re-issued NFC card
    Given I am on a route
    And I have a re-issued NFC card
    When I tap the re-issued NFC card with a expired ticket on the left NFC reader
    Then The ticket is expired message should be displayed

  @ignore
    # perform a successful transaction using a disabled NFC card
  Scenario: Perform a successful disabled NFC card transaction
    Given I am on a route
    And I have a disabled NFC card
    When I tap the disabled NFC card on the left NFC reader
    Then The card is disabled message should be displayed

  @ignore
    # perform a successful day not allowed transaction using NFC card with a valid ticket
  Scenario: Perform a successful day not allowed transaction using NFC card with a valid ticket
    Given I am on a route
    And I have a valid ticket on NFC card that does not work on the given day
    When I tap NFC card with active ticket on the left NFC reader
    Then The day is not allowed message should be displayed

  @ignore
    # perform a successful new main leg not allowed within transfer leg transaction using NFC card with a valid ticket
  Scenario: Perform a successful new main leg not allowed within transfer leg transaction using NFC card with a valid ticket
    Given I am on a route
    And I have a valid ticket on NFC card
    When I perform a main leg transaction
    And I try to perform another main leg transaction within transfer leg
    Then The new main leg not allowed within transfer leg message should be displayed

  @ignore
    # perform a successful no rides available for the day transaction using NFC card with a valid ticket
  Scenario: Perform a successful no rides available for the day transaction using NFC card with a valid ticket
    Given I am on a route
    And I have a valid ticket on NFC card where all the rides for the given day are availed
    When I tap NFC card with active ticket on the left NFC reader
    Then The no rides available for the day message should be displayed

  @ignore
    # perform a successful trips depleted transaction using NFC card with a valid ticket
  Scenario: Perform a successful trips depleted transaction using NFC card with a valid ticket
    Given I am on a route
    And I have a valid ticket on NFC card with zero rides available
    When I tap NFC card with active ticket on the left NFC reader
    Then The trips depleted message should be displayed

  @ignore
    # perform a successful pass limit exceeded transaction using NFC card with a valid ticket
  Scenario: Perform a successful pass limit exceeded transaction using NFC card with a valid ticket
    Given I am on a route
    And I have a valid ticket on NFC card with pass limit exceeded
    When I tap NFC card with active ticket on the left NFC reader
    Then The pass limit message should be displayed

  @ignore
    # perform a successful ride date is invalid transaction using NFC card with a valid ticket
  Scenario: Perform a successful ride date is invalid transaction using NFC card with a valid ticket
    Given I am on a route
    And I have a valid ticket on NFC card with invalid ride date
    When I tap NFC card with active ticket on the left NFC reader
    Then The ride date is invalid message should be displayed

  @ignore
    # perform a successful route not allowed transaction using NFC card with a valid ticket
  Scenario: Perform a successful route not allowed transaction using NFC card with a valid ticket
    Given I am on a route
    And I have a valid ticket on NFC card with route not allowed
    When I tap NFC card with active ticket on the left NFC reader
    Then The route not allowed message should be displayed

  @ignore
    # perform a successful Station not allowed transaction using NFC card with a valid ticket
  Scenario: Perform a successful Station not allowed transaction using NFC card with a valid ticket
    Given I am on a route
    And I have a valid ticket on NFC card with station not allowed
    When I tap NFC card with active ticket on the left NFC reader
    Then The station is not allowed message should be displayed

  @ignore
    # perform a successful transfer not allowed transaction using NFC card with a valid ticket
  Scenario: Perform a successful transfer not allowed transaction using NFC card with a valid ticket
    Given I am on a route
    And I have a valid ticket on NFC card with no transfer
    When I tap NFC card with active ticket on the left NFC reader
    Then The transfer not allowed message should be displayed

  @ignore
    # perform a successful tapped already transaction using NFC card with a valid ticket
  Scenario: Perform a successful tapped already transaction using NFC card with a valid ticket

  @ignore
    # can be performed on Positioning route only
    # perform a successful transaction not allowed transaction using NFC card with a valid ticket
  Scenario: Perform a successful transaction not allowed transaction using NFC card with a valid ticket

  @ignore
    # perform a successful incorrect contract transaction using NFC card with a valid ticket
  Scenario: Perform a successful incorrect contract transaction using NFC card with a valid ticket
    Given I am on a route
    And I have a valid ticket on NFC card with different business unit
    When I tap NFC card with active ticket on the left NFC reader
    Then The incorrect contract message should be displayed

  @ignore
    # appears when a ticket throws an exception
    # perform a successful error in choosing valid ticket transaction using NFC card with a valid ticket
  Scenario: Perform a successful error in choosing valid ticket transaction using NFC card with a valid ticket

  @ignore
    # perform a successful error in uid matching transaction using NFC card with a valid ticket
  Scenario: Perform a successful error in uid matching transaction using NFC card with a valid ticket
    Given I am on a route
    And I have a valid ticket on NFC card of a different NFC card
    When I tap NFC card with active ticket on the left NFC reader
    Then The error in uid matching message should be displayed