package stepDefinitions;

import io.cucumber.java.en.Given;
import utilities.Driver;
import utilities.ReusableMethods;

public class ApkStepDef {
    ReusableMethods reusableMethods=new ReusableMethods();
    @Given("Kullanici uygulamayi yukler")
    public void kullanici_uygulamayi_yukler() {
        reusableMethods.bekle(3);
        Driver.getAndroidDriver();

    }
}
