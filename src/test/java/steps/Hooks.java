package steps;

import io.cucumber.java.*;
import tests.TestBase;
import utils.AppiumServiceInitializer;

public class Hooks extends TestBase {
    static AppiumServiceInitializer appiumServiceInitializer = new AppiumServiceInitializer();
    static int port = 4723;

    @BeforeAll
    public static void beforeAll() {
        if(!appiumServiceInitializer.checkIfServerIsRunnning(port)) {
            appiumServiceInitializer.startAppiumServer();
        } else {
            System.out.println("Appium Server already running on Port - " + port);
        }
    }
    @Before
    public void before(Scenario scenario) {
        androidSetUpPDNoResetFalse();
//        androidSetUpPDNoResetTrue();
        beforeScenario(scenario);
    }

    @After
    public void after(Scenario scenario) {
        afterScenario(scenario);
        tearDown();
    }

    @AfterAll
    public static void afterAll() {
        if(appiumServiceInitializer.checkIfServerIsRunnning(port)) {
            appiumServiceInitializer.stopAppiumServer();
        } else {
            System.out.println("Appium Server already stopped on Port - " + port);
        }
    }
}
