package steps;

import io.cucumber.java.*;
import tests.TestBase;
import utils.AppiumServiceInitializer;

public class Hooks extends TestBase {
    static AppiumServiceInitializer appiumServiceInitializer = new AppiumServiceInitializer();
    static int port = 4723;

    @BeforeAll
    public static void beforeAll() {
        if (!appiumServiceInitializer.checkIfServerIsRunnning(port)) {
            appiumServiceInitializer.startAppiumServer();
            System.out.println("Appium Server Started on Port: " + port);
        } else {
            System.out.println("Appium Server already running on Port - " + port);
        }
    }

    @Before(order = 1)
    public void before(Scenario scenario) {
//        androidSetUpPDNoResetFalse();
//        androidSetUpPDNoResetTrue();
        Android_SetUp_NoResetFalse();
//        Android_SetUp_NoResetTrue();
        beforeScenario(scenario);
        System.out.println("Setup AndroidDriver Successfully");
    }

    @After(order = 1)
    public void after(Scenario scenario) {
        afterScenario(scenario);
        tearDown();
        System.out.println("Tear Down AndroidDriver Successfully");
    }

    @AfterAll
    public static void afterAll() {
        if (appiumServiceInitializer.checkIfServerIsRunnning(port)) {
            appiumServiceInitializer.stopAppiumServer();
            System.out.println("Appium Server Terminated - " + port);
        } else {
            System.out.println("Appium Server already stopped on Port - " + port);
        }
    }
}
