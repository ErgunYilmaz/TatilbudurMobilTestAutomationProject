package stepDefinitions;
import io.cucumber.java.en.When;
import io.cucumber.plugin.event.HookTestStep;
import org.junit.Assert;
import pages.hotelCategoryPage;
import pages.tatilbudurPage;
import utilities.ReusableMethods;

import java.time.LocalDate;

import static utilities.ReusableMethods.logger;


public class SearchHotelDef {
    tatilbudurPage tatilbudurPage = new tatilbudurPage();
    ReusableMethods reusableMethods=new ReusableMethods();
    hotelCategoryPage hotelCategoryPage = new hotelCategoryPage();

    @When("Anasayfada otel arama alanina tiklanir")
    public void anasayfada_otel_arama_alanina_tiklanir() {
        Assert.fail("Bilerek hata fırlatıldı - test senaryosu kontrolü");
        reusableMethods.bekleTiklanabilir(tatilbudurPage.hotelSearchlocation);
    }
    @When("Otel arama alanina {string} adi yazilir")
    public void otel_arama_alanina_adi_yazilir(String otelSehirBolgeTema) {
        hotelCategoryPage.hotelSearchModal.sendKeys(otelSehirBolgeTema);

    }
    @When("Aranilan kategoriye gore listelenen verilerden ilki secilir")
    public void aranilan_kategoriye_gore_listelenen_verilerden_ilki_secilir() {
        reusableMethods.bekleTiklanabilir(hotelCategoryPage.clickByContent);

    }
    @When("Tarih secim alanina tiklanir.")
    public void tarih_secim_alanina_tiklanir() {
       reusableMethods.bekleTiklanabilir(tatilbudurPage.hotelSearchDate);
    }
    @When("Giris tarihi olarak bugunden {string} gun sonrasinin secilmesi saglanir")
    public void giris_tarihi_olarak_bugunden_gun_sonrasinin_secilmesi_saglanir(String gun) {
        ReusableMethods.bekle(1);
        int gunSayisi = Integer.parseInt(gun); // String → int dönüşüm
        ReusableMethods.selectDateFromToday(gunSayisi);
        ReusableMethods.bekle(1);
    }

    @When("Cikis tarihi olarak bugunden {string} gun sonrasinin secilmesi saglanir")
    public void cikis_tarihi_olarak_bugunden_gun_sonrasinin_secilmesi_saglanir(String gun) {
        int gunSayisi = Integer.parseInt(gun);
        ReusableMethods.selectDateFromToday(gunSayisi);
        ReusableMethods.bekle(1);
    }

    @When("Uygulama butonuna tiklanir")
    public void uygulama_butonuna_tiklanir() {
        reusableMethods.bekleTiklanabilir(tatilbudurPage.applyDate);
    }
    @When("Yetiskin sayısı belirleme alaninia tiklanir")
    public void yetiskin_sayısı_belirleme_alaninia_tiklanir() {
       reusableMethods.bekleTiklanabilir(tatilbudurPage.hotelSearchCustomer);
    }
    @When("Acilan ekranda uygula butonuna tiklanir")
    public void acilan_ekranda_uygula_butonuna_tiklanir() {
       reusableMethods.bekleTiklanabilir(tatilbudurPage.customerNumberApplyButton);
    }
    @When("Otel arama islemi baslatilir")
    public void otel_arama_islemi_baslatilir() {
        reusableMethods.bekleTiklanabilir(tatilbudurPage.hotelSearch);
        reusableMethods.bekle(3);
    }
}
