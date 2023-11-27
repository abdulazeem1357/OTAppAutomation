package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tests.TestBase;
import utils.AppConstants;

public class Trip extends TestBase {
    @Given("I am on the shift page")
    public void iAmOnTheShiftPage() {
        softAssert.assertEquals(shiftPage.getShiftPageTitle(), AppConstants.SHIFT_PAGE_TITLE);
    }

    @When("I click on the start trip button")
    public void iClickOnTheStartTripButton() {
        shiftPage.clickStartTripButton();
    }

    @Then("The trips page should be opened")
    public void theTripsPageShouldBeOpened() {
        softAssert.assertEquals(tripPage.getSelectRoutePageText(), AppConstants.TRIP_PAGE_TITLE);
    }

    @And("I search for a {string} route")
    public void iSearchForARoute(String routeID) {
        tripPage.searchForRoute(routeID);
    }

    @When("I click on the route")
    public void iClickOnTheRoute() {
        tripPage.clickOntRoute(AppConstants.ROUTE_NAME);
    }

    @Then("The select route direction pop up should be opened")
    public void theSelectRouteDirectionPopUpShouldBeOpened() {
        softAssert.assertEquals(tripPage.getSelectRouteDirectionDialogueHeader(), AppConstants.ROUTE_DIRECTION_DIALOGUE_TITLE);
    }

    @When("I click on the route number to start trip")
    public void iClickOnTheRouteNumberToStartTrip() {
        tripPage.startRouteButton();
    }

    @Then("The route {string} should be started successfully")
    public void theRouteShouldBeStartedSuccessfully(String routeID) {
        softAssert.assertTrue(routePage.getRouteName().contains(routeID));
    }

    @When("I click on the end trip button")
    public void iClickOnTheEndTripButton() {
        sideMenuComponent.clickEndTripButton();
    }

    @Then("The end trip dialogue should be opened")
    public void theEndTripDialogueShouldBeOpened() {
        softAssert.assertEquals(sideMenuComponent.getAlertDialogueTitle(), "End Trip");
    }

    @When("I click on the end trip dialogue yes button")
    public void iClickOnTheEndTripDialogueYesButton() {
        sideMenuComponent.clickAlertDialogueYesButton();
    }

    @Then("The trip should end")
    public void theTripShouldEnd() {
    }

    @And("The end trip {string} page should be opened")
    public void theEndTripPageShouldBeOpened(String routeID) {
        System.out.println("Route ID: " + routeID);
        System.out.println("Get End Trip Route ID: " + tripPage.getEndTripRoute());
        softAssert.assertTrue(tripPage.getEndTripRoute().contains(routeID));
    }

    @And("The total cash transaction should be {string}")
    public void theTotalCashTransactionShouldBe(String totalCashTransaction) {
        softAssert.assertEquals(tripPage.getTotalCashTransaction(), totalCashTransaction);
    }

    @And("The total cash passengers count should be {string}")
    public void theTotalCashPassengersCountShouldBe(String totalCashPassenger) {
        softAssert.assertEquals(tripPage.getTotalCashPassenger(), totalCashPassenger);
    }

    @And("The total transactions should be {string}")
    public void theTotalTransactionsShouldBe(String totalTransaction) {
        softAssert.assertEquals(tripPage.getTotalTransaction(), totalTransaction);
    }

    @And("The total passengers should be {string}")
    public void theTotalPassengersShouldBe(String totalPassenger) {
        softAssert.assertEquals(tripPage.getTotalPassenger(), totalPassenger);
    }

    @When("I click on the end trip page cancel button")
    public void iClickOnTheCancelButton() {
        tripPage.clickEndTripCancelButton();
    }
}