package steps;

import cucumber.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.SoftAssertManager;
import pageObjects.RoutePage;
import utils.AppConstants;

public class CashTicket {

    TestContext testContext;
    RoutePage routePage;

    public CashTicket(TestContext context) {
        testContext = context;
        routePage = testContext.getPageObjectManager().getRoutePage();
    }

    @Given("I am on a route")
    public void iAmOnARoute() {
        SoftAssertManager.getSoftAssert().assertEquals(routePage.getReadyForTapPageBodyText(), AppConstants.ROUTE_PAGE_BODY_TEXT);
    }

    @When("I click to open select fare menu")
    public void iClickToOpenSelectFareMenu() {
        routePage.clickCashTicketMenuButton();
    }

    @Then("The select fare menu should be opened")
    public void theSelectFareMenuShouldBeOpened() {
        SoftAssertManager.getSoftAssert().assertEquals(routePage.getMenuHeaderText(), AppConstants.FARE_MENU_HEADER_TITLE);
    }

    @When("I select a fare of {string} rands")
    public void iSelectAFareOfRands(String farePrice) {
        routePage.clickRandFareMenuItem(farePrice);
    }

    @Then("The select destinations menu should be opened")
    public void theSelectDestinationsMenuShouldBeOpened() {
        SoftAssertManager.getSoftAssert().assertEquals(routePage.getMenuHeaderText(), AppConstants.DESTINATION_MENU_HEADER_TITLE);
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

    @When("I navigate to the {string} station")
    public void iNavigateToTheStation(String destinationStation) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        routePage.clickStation(destinationStation);
    }

    @Then("The total amount should be {string}")
    public void theTotalAmountShouldBe(String cashAmount) {
        SoftAssertManager.getSoftAssert().assertEquals(routePage.getPassengerFare(), cashAmount);
    }

    @And("The passenger in count should be {string}")
    public void thePassengerInCountShouldBe(String passengerInCount) {
        SoftAssertManager.getSoftAssert().assertEquals(String.valueOf(routePage.getTotalTapInCounterText()), passengerInCount);
    }

    @And("The passenger cash ticket counter should be {string}")
    public void thePassengerCashTicketCounterShouldBe(String cashTicketCounter) {
        SoftAssertManager.getSoftAssert().assertEquals(String.valueOf(routePage.getCashTicketCounterText()), cashTicketCounter);
    }

    @And("The passenger out count should be {string}")
    public void thePassengerOutCountShouldBe(String passengerOutCount) {
        SoftAssertManager.getSoftAssert().assertEquals(String.valueOf(routePage.getTotalTapOutCounterText()), passengerOutCount);
    }

    @When("I remove ticket for {string} passenger")
    public void iRemoveTicketForPassenger(String passengerCount) {
        int passengerCounts = Integer.parseInt(passengerCount);
        for (int i = 1; i <= passengerCounts; i++) {
            routePage.clickRemovePassengerButton();
        }
    }

    @And("The ticket passengers count should be {string}")
    public void theTicketPassengersCountShouldBe(String ticketPassengerCount) {
        SoftAssertManager.getSoftAssert().assertEquals(routePage.getPassengerCount(), ticketPassengerCount);
    }

    @Then("The process payment menu should be opened")
    public void theProcessPaymentMenuShouldBeOpened() {
        SoftAssertManager.getSoftAssert().assertEquals(routePage.getProcessPaymentMenuText(), AppConstants.PROCESS_PAYMENT_HEADER_TITLE);
    }
}