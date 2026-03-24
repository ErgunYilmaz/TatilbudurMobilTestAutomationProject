package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import utilities.Driver;
import utilities.ReusableMethods;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class AllureHooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        String rerunMode = System.getProperty("rerunMode");

        if ("true".equalsIgnoreCase(rerunMode)) {
            Allure.addAttachment(
                    "Retry Info",
                    new ByteArrayInputStream(
                            "Bu senaryo retry koşusunda tekrar çalıştırıldı.".getBytes(StandardCharsets.UTF_8)
                    )
            );

            System.out.println("RETRY MODE -> " + scenario.getName());
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ReusableMethods.getScreenshot(Driver.getAndroidDriver());
            Allure.addAttachment("Failed Screenshot", new ByteArrayInputStream(screenshot));
        }

        Driver.quitAppiumDriver();
    }
}