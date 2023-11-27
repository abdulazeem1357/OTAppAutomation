package runners;

import io.cucumber.testng.CucumberOptions;
import tests.TestBase;

// This class defines the CucumberOptions for the feature file to be executed and specifies the glue code package and plugins to be used.
// The AllureCucumber7Jvm plugin is used to generate Allure reports. The "rerun" plugin is used to rerun failed scenarios.
@CucumberOptions(features = {"src/test/resources/features/CashTicket.feature"}
        , glue = {"steps"}
        , tags = "not @ignore"
        , plugin = {
        "pretty",
        "html:target/cucumber-reports",
        "json:target/cucumber.json",
        "junit:target/cucumber.xml",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        "rerun:target/failedrun.txt"
})

// The class extends the TestBase class to include the test setup and teardown methods.
public class TestsRunner extends TestBase {
}