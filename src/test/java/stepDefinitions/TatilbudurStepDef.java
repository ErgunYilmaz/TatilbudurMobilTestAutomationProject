package stepDefinitions;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.tatilbudurPage;
import utilities.Driver;
import utilities.ReusableMethods;

public class TatilbudurStepDef {

    tatilbudurPage page =new tatilbudurPage();

    @Given("Cerezler kabul edilir")
    public void cerezler_kabul_edilir() {

    }
    @Given("Bildirim izni cikarsa kapatilir")
    public void bildirim_izni_cikarsa_kapatilir() {

    }
    @Given("Uygulamanin acildigi kontrol edilir ve gerekli izinler verilir")
    public void uygulamanin_acildigi_kontrol_edilir_ve_gerekli_izinler_verilir() {
        Driver.getAndroidDriver();
        ReusableMethods.bekle(3);
    }
    @When("Anasayfada otel arama alanina {string} yazilir ve secilir")
    public void anasayfada_otel_arama_alanina_yazilir_ve_secilir(String sehir) {
        page.otelSehirBolgeveyaTemaAdiButonu.click();
        ReusableMethods.bekle(2);
        page.otelSehirBolgeveyaTemaAdi2Butonu.sendKeys(sehir);
        page.antalyaBolgeleriButonu.click();
        ReusableMethods.bekle(1);

    }
    @When("Giris tarihi olarak bugunden {string} gun sonrasinin secilmesi saglanir")
    public void giris_tarihi_olarak_bugunden_gun_sonrasinin_secilmesi_saglanir(String gun) {
        page.datePickerButonu.click();
        ReusableMethods.bekle(2);
        int gunSayisi = Integer.parseInt(gun); // String → int dönüşüm
        ReusableMethods.selectDateFromToday(gunSayisi);
        ReusableMethods.bekle(2);
    }
    @When("Cikis tarihi olarak bugunden {string} gun sonrasinin secilmesi saglanir")
    public void cikis_tarihi_olarak_bugunden_gun_sonrasinin_secilmesi_saglanir(String gun) {
        int gunSayisi = Integer.parseInt(gun);
        ReusableMethods.selectDateFromToday(gunSayisi);

    }
    @When("Otel arama islemi baslatilir")
    public void otel_arama_islemi_baslatilir() {

    }
    @Then("Cikan sonuclar fiyata gore artan sekilde siralanir")
    public void cikan_sonuclar_fiyata_gore_artan_sekilde_siralanir() {

    }
    @Then("Sonuclarin fiyata gore artan sekilde listelendigi dogrulanir")
    public void sonuclarin_fiyata_gore_artan_sekilde_listelendigi_dogrulanir() {

    }

}
