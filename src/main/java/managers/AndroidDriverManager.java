package managers;

import fundamentals.InfrastructureEnv;
import helpers.ConfigHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class AndroidDriverManager {
    private AndroidDriver driver;
    ConfigHelper configHelper = new ConfigHelper();

    // URL for the Appium Server, APPIUM variable to store the URL for the Appium server
    private final static String APPIUM = "http://localhost:4723/wd/hub";

    // InfrastructureEnv class object
    InfrastructureEnv env = new InfrastructureEnv();

    // Returns the driver
    public AndroidDriver getDriver() {
        if (driver == null) driver = createDriver();
        return driver;
    }

    // Create driver based on the driverType received from the Infrastructure Class
    private AndroidDriver createDriver() {
        if (Objects.equals(env.getDriverType(), "physicalDeviceNoReset")) {
            driver = physicalDeviceNoReset();
        }
        if (Objects.equals(env.getDriverType(), "physicalDeviceFullReset")) {
            driver = physicalDeviceFullReset();
        }
        if (Objects.equals(env.getDriverType(), "emulatorDeviceNoReset")) {
            driver = emulatorDeviceNoReset();
        }
        if (Objects.equals(env.getDriverType(), "emulatorDeviceFullReset")) {
            driver = emulatorDeviceFullReset();
        }
        System.out.println("Setup Android Driver Successfully");
        return driver;
    }

    // Set up the environment for testing on a physical Android device without clearing its cache and data
    private AndroidDriver physicalDeviceNoReset() {
        UiAutomator2Options capabilities = configHelper.configWithNoResetTruePhysicalDevice();
        try {
            driver = new AndroidDriver(new URL(APPIUM), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }

    // Set up the environment for testing on a physical Android device with a fresh install
    private AndroidDriver physicalDeviceFullReset() {
        UiAutomator2Options capabilities = configHelper.configWithResetFalsePhysicalDevice();
        try {
            driver = new AndroidDriver(new URL(APPIUM), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }

    // Set up the environment for testing on an Android emulator with No Reset
    private AndroidDriver emulatorDeviceNoReset() {
        UiAutomator2Options capabilities = configHelper.configWithNoResetTrue();
        try {
            driver = new AndroidDriver(new URL(APPIUM), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }

    // Set up the environment for testing on an Android emulator with Full Reset
    private AndroidDriver emulatorDeviceFullReset() {
        UiAutomator2Options capabilities = configHelper.configWithResetFalse();
        try {
            driver = new AndroidDriver(new URL(APPIUM), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }

    // Clean up resources after each scenario and make sure that the Android driver is closed.
    public void quitDriver() {
        try {
            if (null != driver) {
                driver.quit();
                System.out.println("Tear Down Android Driver Successfully");
            }
        } catch (Exception e) {
            System.out.println("Could not Quit Android Driver Successfully Because: " + e.getMessage());
        }
    }
}
