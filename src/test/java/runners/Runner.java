package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-reports/regression.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "rerun:target/rerun.txt"
        },
        features = "src/test/resources/Features",
        glue = {"stepDefinitions","utilities","hooks"},
        tags = "@E",
        dryRun = false
)
public class Runner {
}