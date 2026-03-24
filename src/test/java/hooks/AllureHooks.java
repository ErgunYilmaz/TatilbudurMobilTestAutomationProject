package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import utilities.Driver;
import utilities.ReusableMethods;

import java.io.ByteArrayInputStream;

public class AllureHooks {

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ReusableMethods.getScreenshot(Driver.getAndroidDriver());
            Allure.addAttachment("Failed Screenshot", new ByteArrayInputStream(screenshot));
        }
        Driver.quitAppiumDriver();
    }
}