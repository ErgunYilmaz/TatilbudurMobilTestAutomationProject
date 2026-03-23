package stepDefinitions;

import io.cucumber.java.en.Given;
import pages.tatilbudurPage;
import utilities.Driver;
import utilities.ReusableMethods;

public class CommonStepsDef {
    ReusableMethods reusableMethods=new ReusableMethods();
    tatilbudurPage tatilbudurPage = new tatilbudurPage();
    @Given("Cerezler kabul edilir")
    public void cerezler_kabul_edilir() {

    }
    @Given("Bildirim izni cikarsa kapatilir")
    public void bildirim_izni_cikarsa_kapatilir() {

    }
    @Given("Uygulamanin acildigi kontrol edilir ve gerekli izinler verilir")
    public void uygulamanin_acildigi_kontrol_edilir_ve_gerekli_izinler_verilir() {
        Driver.getAndroidDriver();
        reusableMethods.bekleTiklanabilir(tatilbudurPage.registerLabel);

    }
}
