package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-reports/regression.html",   // klasik HTML raporu
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" // Allure entegrasyonu
        },
        features = "src/test/resources/Features",
        glue = {"stepDefinitions","utilities","hooks"},
        tags = "@Rezervasyon",
        dryRun = false
)
public class Runner {

}
