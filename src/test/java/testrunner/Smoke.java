package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features={"src/test/java/features/smoke.feature"},
        glue={"stepdefinition"},
        plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class Smoke extends AbstractTestNGCucumberTests {
}
