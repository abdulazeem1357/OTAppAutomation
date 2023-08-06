package steps;

import io.cucumber.java.*;
import tests.TestBase;

public class Hooks extends TestBase {
    @BeforeAll
    public static void start() {

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
    public static void end() {
    }
}
