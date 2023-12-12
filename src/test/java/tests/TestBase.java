package tests;

import fundamental.AndroidBasics;
import fundamental.Waiting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.asserts.SoftAssert;
import pageObjects.*;
import utils.ConfigHelper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;

// TestBase class to initialize the Android driver and set up the environment for testing
public class TestBase extends AbstractTestNGCucumberTests {
    protected static DownloadDataPage downloadDataPage;
    protected static HardwareSettingsPage hardwareSettingsPage;
    protected static TerminalSettingsPage terminalSettingsPage;
    protected static LoginToTerminalPage loginToTerminalPage;

    protected static MainPage mainPage;
    protected static RoutePage routePage;
    protected static SettingsPage settingsPage;
    protected static ShiftPage shiftPage;
    protected static SideMenuComponent sideMenuComponent;
    protected static TripPage tripPage;
    protected static AndroidBasics androidBasics;
    protected static Waiting waiting;
    protected static DayPrepPage dayPrepPage;
    protected static ConstantsPage constantsPage;

    // URL for the Appium Server, APPIUM variable to store the URL for the Appium server
    private final static String APPIUM = "http://localhost:4723/wd/hub";

    // driver variable of type AppiumDriver to initialize the Android driver
    private static AndroidDriver driver;

    // SoftAssert to handle assertions in a soft way, meaning that the test case will continue executing even if an assertion fails
    protected static SoftAssert softAssert;

    // Set up the environment for testing on an Android emulator
    public static void Android_SetUp_NoResetTrue() {
        UiAutomator2Options capabilities = ConfigHelper.configWithNoResetTrue();
        try {
            driver = new AndroidDriver(new URL(APPIUM), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Android_SetUp_NoResetFalse() {
        UiAutomator2Options capabilities = ConfigHelper.configWithResetFalse();
        try {
            driver = new AndroidDriver(new URL(APPIUM), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    // Set up the environment for testing on a physical Android device without clearing its cache and data
    public static void androidSetUpPDNoResetTrue() {
        UiAutomator2Options capabilities = ConfigHelper.configWithNoResetTruePhysicalDevice();
        try {
            driver = new AndroidDriver(new URL(APPIUM), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    // Set up the environment for testing on a physical Android device with a fresh install
    public static void androidSetUpPDNoResetFalse() {
        UiAutomator2Options capabilities = ConfigHelper.configWithResetFalsePhysicalDevice();
        try {
            driver = new AndroidDriver(new URL(APPIUM), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    // Clean up resources after each scenario and make sure that the Android driver is closed.
    public static void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }

    // Method for logging before starting a scenario
    public void beforeScenario(Scenario scenario) {
        createSoftAssertionObject();
        pageInitializer();
        System.out.println("/*****************************************************************************************************/");
        System.out.println("Starting Scenario - " + scenario.getName());
        System.out.println("Scenario Id :  - " + scenario.getId());
        System.out.println("Scenario Tags :  - " + scenario.getSourceTagNames());
        System.out.println("/*****************************************************************************************************/");
    }

    // Method for taking a screenshot and logging the scenario status after completion of the scenario,
    // and adding the screenshot as an attachment to the Allure report in case of failure.
    public void afterScenario(Scenario scenario) {
        System.out.println("/------------------------------------------------------------------------------------------------------/");
        System.out.println(scenario.getName() + " Status - " + scenario.getStatus());
        if (scenario.isFailed()) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String rootDirectoryPath = System.getProperty("user.dir");
            String screenShotDirectory = rootDirectoryPath + "/screenshots/" + LocalDate.now().toString() + "/";
            File screenshotFolder = new File(screenShotDirectory);
            screenshotFolder.mkdirs();
            String screenShotFilePath = screenshotFolder + "/" + LocalTime.now().toString().replaceAll(":", "") + " " + scenario.getName() + ".jpg";
            File targetFile = new File(screenshotFolder + "/" + LocalTime.now().toString().replaceAll(":", "") + " " + scenario.getName() + ".jpg");
            try {
                FileUtils.copyFile(screenshot, targetFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Path screenShotContent = Paths.get(screenShotFilePath);
            try (InputStream is = Files.newInputStream(screenShotContent)) {
                Allure.addAttachment("Failure ", is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("/------------------------------------------------------------------------------------------------------/");
        AssertAndDestroySoftAssetObject();
    }

    // initializes all the page objects
    private void pageInitializer() {
        if (driver == null) {
            System.out.println("Cannot initialize pageObjects when Driver is NULL");
        } else {
            downloadDataPage = new DownloadDataPage(driver);
            hardwareSettingsPage = new HardwareSettingsPage(driver);
            terminalSettingsPage = new TerminalSettingsPage(driver);
            loginToTerminalPage = new LoginToTerminalPage(driver);
            mainPage = new MainPage(driver);
            routePage = new RoutePage(driver);
            settingsPage = new SettingsPage(driver);
            shiftPage = new ShiftPage(driver);
            sideMenuComponent = new SideMenuComponent(driver);
            tripPage = new TripPage(driver);
            androidBasics = new AndroidBasics(driver);
            waiting = new Waiting(driver);
            dayPrepPage = new DayPrepPage(driver);
            constantsPage = new ConstantsPage(driver);
        }
    }

    // creates new softAssert object
    private void createSoftAssertionObject() {
        if (softAssert == null) {
            softAssert = new SoftAssert();
        }
    }

    // assert all the softAsserts and set the softAssert to null
    private void AssertAndDestroySoftAssetObject() {
        if (softAssert != null) {
            softAssert.assertAll();
            softAssert = null;
        } else {
            System.out.println("Soft Assert is Already Null");
        }
    }
}