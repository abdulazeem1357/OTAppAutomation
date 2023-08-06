package pageObjects;

import fundamental.AndroidBasics;
import fundamental.Waiting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class RoutePage extends BasePage {
    private final Waiting waiting;
    private static final String EXPIRED_NFC_TICKET_MESSAGE = "product has expired";
    private static final String ROUTE_PAGE_BODY_TEXT = "Ready For Tap";

    public RoutePage(AndroidDriver androidDriver) {
        super(androidDriver);
        waiting = new Waiting(androidDriver);
    }

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/txtViewRouteName")
    private WebElement routeName;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/txtViewNfcStatus")
    private WebElement bodyText;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/linearLayoutTapToggleButton")
    private WebElement cashTicketMenuButton;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.TextView")
    private WebElement menuHeaderText;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/txtViewPaymentTitleAfterStationNameTop")
    private WebElement processPaymentMenu;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/imgViewMinusPassenger")
    private WebElement removePassengerButton;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/imgViewAddPassenger")
    private WebElement addPassengerButton;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/txtViewNoOfPassengers")
    private WebElement passengerCount;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/txtViewTotalFare")
    private WebElement passengerFare;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/layoutLinearProcessCash")
    private WebElement cashTicketPurchaseButton;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/txtViewTapInCounter")
    private WebElement totalTapInCounter;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/txtViewTapOutCounter")
    private WebElement totalTapOutCounter;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/txtViewTapInCashCounter")
    private WebElement cashTicketCounter;

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/txtViewTapInCardCounter")
    private WebElement NFCTicketCounter;

    public String getRouteName() {
        return waiting.waitForElementVisibility(routeName).getText();
    }

    public String getReadyForTapPageBodyText() {
        return waiting.waitForTextToBePresentInElement(bodyText, ROUTE_PAGE_BODY_TEXT).getText();
    }

    public String getExpiredNFCBodyText() {
        return waiting.waitForTextToBePresentInElement(bodyText, EXPIRED_NFC_TICKET_MESSAGE).getText();
    }

    public void clickCashTicketMenuButton() {
        waiting.click(cashTicketMenuButton);
    }

    public String getMenuHeaderText() {
        return waiting.waitForElementVisibility(menuHeaderText).getText();
    }

    public void clickRandFareMenuItem(String farePrice) {
        waiting.waitForTextToBePresentInList(getMenuItemsList(), farePrice).click();
    }

    public void clickDestination(String destination) {
//        waiting.waitForTextToBePresentInList(getMenuItemsList(), destination).click();
        waiting.waitAndSwipeForElementWithDesiredTextToBeFound(getMenuItemsList(), destination, AndroidBasics.Direction.VERTICAL).click();
    }

    public void clickStation(String stationName) {
        waiting.waitAndSwipeForElementWithDesiredTextToBeFound(getStationsList(), stationName, AndroidBasics.Direction.HORIZONTAL).click();
    }

    public void clickCashTicketPurchaseButton() {
        waiting.click(cashTicketPurchaseButton);
    }

    public String getTotalTapInCounterText() {
        return waiting.waitForElementVisibility(totalTapInCounter).getText();
    }

    public String getTotalTapOutCounterText() {
        return waiting.waitForElementVisibility(totalTapOutCounter).getText();
    }

    public String getCashTicketCounterText() {
        return waiting.waitForElementVisibility(cashTicketCounter).getText();
    }

    public String getNFCTicketCounterText() {
        return waiting.waitForElementVisibility(NFCTicketCounter).getText();
    }

    public void clickAddPassengerButton() {
        waiting.click(addPassengerButton);
    }

    public void clickRemovePassengerButton() {
        waiting.click(removePassengerButton);
    }

    public String getPassengerCount() {
        return waiting.waitForElementVisibility(passengerCount).getText();
    }

    public String getPassengerFare() {
        return waiting.waitForElementVisibility(passengerFare).getText();
    }

    public String getProcessPaymentMenuText() {
        return waiting.waitForElementVisibility(processPaymentMenu).getText();
    }

    public String getMenuItemsList() {
        return "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.GridView/android.widget.FrameLayout[%d]/android.widget.LinearLayout/android.widget.TextView";
    }

    public String getStationsList() {
        return "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[%d]/android.widget.TextView";
    }
}