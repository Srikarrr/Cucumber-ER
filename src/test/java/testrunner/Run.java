package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions(
        features={"src/test/java/features/login.feature","src/test/java/features/logout.feature","src/test/java/features/failure.feature"},
        glue={"stepdefinition"},
        plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class Run  extends AbstractTestNGCucumberTests {
}
