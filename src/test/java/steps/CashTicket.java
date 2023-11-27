package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tests.TestBase;
import utils.AppConstants;

import java.time.Duration;

public class CashTicket extends TestBase {
    @Given("I am on a route")
    public void iAmOnARoute() {
        softAssert.assertEquals(routePage.getReadyForTapPageBodyText(), AppConstants.ROUTE_PAGE_BODY_TEXT);
    }

    @When("I click to open select fare menu")
    public void iClickToOpenSelectFareMenu() {
        routePage.clickCashTicketMenuButton();
    }

    @Then("The select fare menu should be opened")
    public void theSelectFareMenuShouldBeOpened() {
        softAssert.assertEquals(routePage.getMenuHeaderText(), AppConstants.FARE_MENU_HEADER_TITLE);
    }

    @When("I select a fare of {string} rands")
    public void iSelectAFareOfRands(String farePrice) {
        routePage.clickRandFareMenuItem(farePrice);
    }

    @Then("The select destinations menu should be opened")
    public void theSelectDestinationsMenuShouldBeOpened() {
        softAssert.assertEquals(routePage.getMenuHeaderText(), AppConstants.DESTINATION_MENU_HEADER_TITLE);
    }

    @When("I select a {string} destination")
    public void iSelectADestination(String destinationStation) {
        routePage.clickDestination(destinationStation);
    }

    @And("I select ticket for {string} passenger")
    public void iSelectTicketForPassenger(String passengerCount) {
        int passengerCounts = Integer.parseInt(passengerCount);
        if (passengerCounts != 1) {
            for (int i = 1; i < passengerCounts; i++) {
                routePage.clickAddPassengerButton();
            }
        }
    }

    @When("I click to purchase ticket for passengers")
    public void iClickToPurchaseTicketForPassenger() {
        routePage.clickCashTicketPurchaseButton();
    }

    @Then("The ticket should be purchased successfully")
    public void theTicketShouldBePurchasedSuccessfully() {
        int totalTap = Integer.parseInt(routePage.getTotalTapInCounterText());
        softAssert.assertTrue(totalTap > 0);

        int cashTapIn = Integer.parseInt(routePage.getCashTicketCounterText());
        softAssert.assertTrue(cashTapIn > 0);
    }

    @And("The passenger in count should increase by {string}")
    public void thePassengerCountShouldIncreaseBy(String tapInPassengerCount) {
    }

    @And("Updated cash tickets should be displayed by increase of {string}")
    public void updatedCashTicketsShouldBeDisplayedByIncreaseOf(String numberOfCashTickets) {
    }

    @When("I navigate to the {string} station")
    public void iNavigateToTheStation(String destinationStation) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        routePage.clickStation(destinationStation);
    }

    @Then("The station should be changed")
    public void theStationShouldBeChanged() {
    }

    @And("The passenger should be marked as out")
    public void thePassengerShouldBeMarkedAsOut() {
    }

    @And("The passenger out count should increase by {string}")
    public void thePassengerOutCountShouldIncreaseBy(String tapOutPassengerCount) {
//        softAssert.assertEquals(routePage.getTotalTapOutCounterText(), TOTAL_TAP_OUT_PASSENGER_COUNT);
        int totalTapOut = Integer.parseInt(routePage.getTotalTapOutCounterText());
        softAssert.assertTrue(totalTapOut > 0);
    }
}