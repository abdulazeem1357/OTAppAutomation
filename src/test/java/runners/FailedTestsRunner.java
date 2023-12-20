package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// Class for rerunning the failed scenarios with CucumberOptions annotations
// features parameter is set to "@target/failedrun.txt" to run only the failed tests from the previous run
@CucumberOptions(features = {"@target/failedrun.txt"}
        , glue = {"steps"}
        , tags = "not @ignore"
        , monochrome = true
        , plugin = {
        "pretty",
        "html:target/cucumber-reports",
        "json:target/cucumber.json",
        "junit:target/cucumber.xml",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        "rerun:target/failedrun.txt"
})

// Extends the TestBase class for setup and teardown methods
public class FailedTestsRunner extends AbstractTestNGCucumberTests {
}