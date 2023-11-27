package steps;

import fundamental.AndroidBasics;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.TestBase;
import utils.AppConstants;

import java.time.Duration;

public class LoginToShift extends TestBase {
    @When("I navigate to the main page from download page")
    public void iNavigateToTheMainPageFromDownloadPage() {
        downloadDataPage.clickBackButton();
    }

    @When("I click on login to shift button")
    public void iClickOnLoginToShiftButton() {
        mainPage.clickLoginShiftButton();
    }

    @Then("The driver shift login popup should be opened")
    public void theDriverShiftLoginPopupShouldBeOpened() {
        softAssert.assertEquals(mainPage.getDriverShiftLoginDialogueHeader(), AppConstants.DRIVER_SHIFT_DIALOGUE_TITLE);
    }

    @And("I enter the {string} driver ID")
    public void iEnterTheDriverID(String driverID) {
        mainPage.enterDriverID(driverID);
    }

    @And("I enter the {string} driver pin")
    public void iEnterTheDriverPin(String driverPin) {
        mainPage.enterDriverPin(driverPin);
    }

    @And("I click on the login to shift button")
    public void iClickOnTheLoginToShiftButton() {
        mainPage.clickDriverShiftLoginButton();
    }

    @Then("The adhoc shift pop up should be opened")
    public void theAdhocShiftPopUpShouldBeOpened() {
        softAssert.assertEquals(mainPage.getAdHocAlertTitle(), AppConstants.ADHOC_ALERT_TITLE);
    }

    @When("I click on the continue to adhoc shift button")
    public void iClickOnTheContinueToAdhocShiftButton() {
        mainPage.clickContinueShiftButton();
    }

    @Then("I should be logged in to the shift successfully")
    public void iShouldBeLoggedInToTheShiftSuccessfully() {
        softAssert.assertEquals(shiftPage.getShiftPageTitle(), AppConstants.SHIFT_PAGE_TITLE);
    }

    @And("The shift page should be opened")
    public void theShiftPageShouldBeOpened() {
        softAssert.assertEquals(shiftPage.getShiftPageTitle(), AppConstants.SHIFT_PAGE_TITLE);
    }

    @When("I click on the end shift button")
    public void iClickOnTheEndShiftButton() {
        sideMenuComponent.clickEndShiftButton();
    }

    @Then("The shift should end")
    public void theShiftShouldEnd() {
        softAssert.assertEquals(sideMenuComponent.getEndShiftDialogueTitle(), AppConstants.END_SHIFT_DIALOGUE_TITLE);
        softAssert.assertEquals(shiftPage.getShiftPageTitle(), AppConstants.SHIFT_PAGE_TITLE);
    }

    @And("I enter the {string} wayBill")
    public void iEnterTheWayBill(String wayBill) {
        mainPage.enterWayBill(wayBill);
    }

    @Then("I should see the toast message {string} after attempting to driver shift login")
    public void iShouldSeeTheToastMessageAfterAttemptingToDriverShiftLogin(String toastMessage) {
        softAssert.assertEquals(mainPage.getToastMessage(), toastMessage);
    }

    @When("I click on the cancel adhoc shift button")
    public void iClickOnTheCancelAdhocShiftButton() {
        mainPage.clickCancelShiftButton();
    }
}