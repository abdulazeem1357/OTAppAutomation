package pageObjects;

import fundamentals.Waiting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ConstantsPage extends BasePage {
    public static final String DAY_END_CHECK_PAGE_TITLE = "Day End Checks";
    public static final String DAY_START_CHECK_PAGE_TITLE = "Day Start Checks";
    public static final String FIRMWARE_ALREADY_UPDATE = "Firmware already up to date";
    public static final String DATA_DOWNLOAD_SUCCESSFUL = "Data download completed: \n" + "Publication Data downloaded successfully";
    public static final String RECONCILIATION_SUCCESSFUL = "Reconciliation is successfully completed";
    public static final String DOWNLOAD_SUCCESS_MESSAGE = "Data download completed: \n" + "Publication Data downloaded successfully";
    public static final String DOWNLOAD_FAILURE_MESSAGE = "Publication download failed: Unable to get publication list from server";
    public static final String GPS_ALERT_TITLE = "Use GPS for location";
    public static final String GPS_STATUS_NO = "No";
    public static final String ALERT_DIALOGUE_NO_BUTTON = "No";
    public static final String ALERT_DIALOGUE_YES_BUTTON = "Yes";
    public static final String ALERT_DIALOGUE_IF_AVAILABLE_BUTTON = "If available";
    public static final String TEXT_ATTRIBUTE = "text";
    public static final String USE_GPS_FOR_LOCATION = "Use GPS for location";
    public static final String USER_ID_BUTTON = "User ID";
    public static final String PASSWORD_BUTTON = "Password";
    public static final String TERMINAL_SERVER = "Terminal Server";
    public static final String LOGIN_BUTTON = "Login";
    public static final String LOGIN_TERMINAL_BODY_TEXT = "Please login to OpenTransit..!!";
    public static final String DOWNLOAD_DATA_BODY_TEXT = "Please download data..!!";
    public static final String LOGIN_SHIFT_BODY_TEXT = "Please login to start shift";
    public static final String GPS_NOT_WORKING_BODY_TEXT = "GPS is not working properly";
    public static final String ADHOC_SHIFT_ALERT_TITLE = "Adhoc Shift";
    public static final String OPEN_TRANSIT_TITLE = "OPENTRANSIT";
    public static final String EXPIRED_NFC_TICKET_MESSAGE = "product has expired";
    public static final String ROUTE_PAGE_BODY_TEXT = "Ready For Tap";
    public static final String hardwareSettingsButton = "Hardware Setting";
    public static final String loginButton = "Log In";
    public static final String ENABLE_TAG_AUTHENTICATE = "Enable Tag Authenticate";
    public static final String AUTO_DISABLE_WIFI = "Auto Disable Wifi";
    public static String terminalSettingsButton = "Terminal Settings";
    public static final String ENABLE_TAG_AUTHENTICATE_ALERT_TITLE = "Enable Tag Authenticate";
    private final Waiting waiting;
    public ConstantsPage(AndroidDriver androidDriver) {
        super(androidDriver);
        waiting = new Waiting(androidDriver);
    }

    @AndroidFindBy(id = "com.bps.bpass.mainpackage:id/toolBarTitle")
    private WebElement pageTitle;

    public String getPageTitle() {
        return waiting.waitForElementVisibility(pageTitle).getText();
    }

}
