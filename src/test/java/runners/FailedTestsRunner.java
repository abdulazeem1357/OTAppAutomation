package runners;

import io.cucumber.testng.CucumberOptions;
import tests.TestBase;

// Class for rerunning the failed scenarios with CucumberOptions annotations
// features parameter is set to "@target/failedrun.txt" to run only the failed tests from the previous run
@CucumberOptions(features = "@target/failedrun.txt"
        , glue = {"steps"}
        , plugin = {
        "pretty",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        "rerun:target/failedrun.txt"
})

// Extends the TestBase class for setup and teardown methods
public class FailedTestsRunner extends TestBase {
}