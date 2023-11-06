package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features={"src/test/java/features/read_packages.feature"},
        glue={"stepdefinition"},
        plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class Regression extends AbstractTestNGCucumberTests {
}
