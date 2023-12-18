Feature: Complete E2E

  @Complete @NFCTicket
  Scenario: Complete run of the OT App

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

      # turn off the authenticate tag worker
    When I click on the enable tag authenticate button
    Then The enable tag authenticate dialogue should be opened
    And I click on the no button on the tag authenticate dialogue
    Then The enable tag authenticate should be set to "No"

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

      # successfully download the data with a healthy internet connection
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

      # login to the ad hoc shift successfully
    Given I am on the main page
    And I send the application to the background
    Then The day prep page should be opened
    And The day prep checks should be completed successfully
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

      # perform cash transaction successfully
    Given I am on a route
    When I click to open select fare menu
    Then The select fare menu should be opened
    When I select a fare of "30.0" rands
    Then The select destinations menu should be opened
    When I select a "Sosh Transfer Station 1" destination
    Then The process payment menu should be opened
    And I select ticket for "1" passenger
    Then The total amount should be "30.00"
    And The ticket passengers count should be "01"
    When I click to purchase ticket for passengers
    And The passenger in count should be "1"
    And The passenger cash ticket counter should be "1"

      # perform a successful transaction using NFC card with an valid ticket
    And I have a valid ticket on NFC card
    When I tap NFC card on the left NFC reader
    Then The "Valid ticket" message should be displayed
    And The passenger in count should be "2"
    And The card ticket counter should be "1"

    # perform a successful transaction with multiple merchants tickets on the NFC card
    And I have multiple valid merchant tickets on the NFC card
    When I tap NFC card on the left NFC reader
    Then The "Valid ticket" message should be displayed
    And The passenger in count should be "3"
    And The card ticket counter should be "2"

      # perform a successful transaction using a re-issued NFC card valid ticket
    And I have a re-issued to NFC card
    When I tap NFC card on the left NFC reader
    Then The "Valid ticket" message should be displayed
    And The passenger in count should be "4"
    And The card ticket counter should be "3"

      # perform a successful new main leg not allowed within transfer leg transaction using NFC card with a valid ticket
    And I have a valid ticket on NFC card with no main and transfer leg
    When I tap NFC card on the left NFC reader
    Then The "Main leg transaction is not allowed" message should be displayed

      # perform a successful product has expired transaction using NFC card with an expired ticket
    And I have an expired ticket on NFC card
    When I tap NFC card on the left NFC reader
    Then The "product has expired" message should be displayed

      # perform a successful error in uid matching transaction using NFC card with a valid ticket
    And I have a valid ticket on NFC card with an different NFC card UID
    When I tap NFC card on the left NFC reader
    Then The "Sorry, invalid card" message should be displayed

      # perform a successful incorrect contract/station not found transaction using NFC card with a valid ticket
    And I have a valid ticket on NFC card with different business unit
    When I tap NFC card on the left NFC reader
    Then The "Station Is Not Allowed" message should be displayed

    # perform a successful no rides available for the day transaction using NFC card with a valid ticket
    And I have a valid ticket on NFC card where all the rides for the given day are availed
    When I tap NFC card on the left NFC reader
    Then The "Rides Are Not Available" message should be displayed

      # perform a successful transfer leg within time transaction using NFC card with an valid ticket
    And I have a valid ticket on NFC card with a transfer leg
    And I navigate to the "Sosh Transfer Station 1" station
    When I tap NFC card on the left NFC reader
    Then The "Valid ticket" message should be displayed
    And The passenger in count should be "5"
    And The card ticket counter should be "4"

      # perform a successful transfer leg not allowed within within transfer time using NFC card with a valid ticket
    And I have a valid ticket on NFC card with no main and transfer leg
    When I tap NFC card on the left NFC reader
    Then The "Transfer leg transaction is not allowed" message should be displayed

     # perform a successful Station not allowed transaction using NFC card with a valid ticket
    And I have a valid ticket on NFC card
    Then I navigate to the "Sosh Transfer Station 5" station
    When I tap NFC card on the left NFC reader
    Then The "Station Is Not Allowed" message should be displayed

    # perform a successful transfer not allowed transaction using NFC card with a valid ticket
    # end trip
    When I click on the side menu button
    Then The side menu should be opened
    When I click on the end trip button
    Then The end trip dialogue should be opened
    When I click on the end trip dialogue yes button
    Then The trip should end
    And The end trip "Sosh Live Route 1 ( Sosh Live Route 1 RP )" page should be opened
    And The total cash transaction should be "1"
    And The total cash passengers count should be "1"
    And The total fare product transaction should be "4"
    And The total fare product passengers count should be "4"
    And The total transactions should be "5"
    And The total passengers should be "5"
    When I click on the end trip page cancel button
    Then The shift page should be opened

      # start an ad hoc shift trip successfully
    Given I am on the shift page
    When I click on the start trip button
    Then The trips page should be opened
    And I search for a "Sosh Live Route 2" route
    When I click on the route
    Then The select route direction pop up should be opened
    When I click on the route number to start trip
    Then The route "Sosh Live Route 2" should be started successfully

    And I navigate to the "Sosh Transfer Station 4" station
    And I have a valid ticket on NFC card
    When I tap NFC card on the left NFC reader
    Then The "Transfer Ride Is Not Allowed" message should be displayed