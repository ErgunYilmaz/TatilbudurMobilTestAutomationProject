package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.netty.util.concurrent.FailedFuture;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.paymentPage;
import utilities.Driver;
import utilities.ReusableMethods;

public class PaymentStepDef {
    paymentPage paymentPage=new paymentPage();
    @Then("Odeme Bilgileri sayfasi acildigi dogrulanir")
    public void odeme_bilgileri_sayfasi_acildigi_dogrulanir() {


    }
    @Then("Odeme ekraninda kart alanlari gorunene kadar asagi kaydirilir")
    public void odeme_ekraninda_kart_alanlari_gorunene_kadar_asagi_kaydirilir() {
        ReusableMethods.scrollWithUiScrollable("Kart ile Öde");
        ReusableMethods.bekleTiklanabilir(paymentPage.payWithCreditCard);
    }
    @Then("Kart uzerindeki isim {string} girilir")
    public void kart_uzerindeki_isim_girilir(String paymentName) {
        ReusableMethods.scrollWithUiScrollable("Hemen Öde");
         ReusableMethods.yaz(paymentPage.paymentCardUserName,paymentName);
        ReusableMethods.bekle(2);
    }
    @Then("Kart numarasi {string} girilir")
    public void kart_numarasi_girilir(String cardNumber) {
        ReusableMethods.yaz(paymentPage.paymentCardNumber,cardNumber);
        ReusableMethods.bekle(2);

    }
    @Then("Son kullanma ayi secilir")
    public void son_kullanma_ayi_secilir() {
        ReusableMethods.bekleTiklanabilir(paymentPage.paymentCardMounth);
        ReusableMethods.selectDialogOptionByIndex(5);
        ReusableMethods.bekle(3);
    }
    @Then("Son kullanma yili secilir")
    public void son_kullanma_yili_secilir() {
       ReusableMethods.bekleTiklanabilir(paymentPage.paymentCardYear);
        ReusableMethods.selectDialogOptionByIndex(3);
        ReusableMethods.bekle(3);
    }
    @Then("CVV {string} girilir")
    public void cvv_girilir(String cvv) {
        ReusableMethods.yaz(paymentPage.cvvInput,cvv);
    }
    @Then("On bilgilendirme formu ve satis sozlesmesi checkboxina tiklanir")
    public void on_bilgilendirme_formu_ve_satis_sozlesmesi_checkboxina_tiklanir() {
        ReusableMethods.scrollWithUiScrollable("Rezervasyonu Tamamla");
        ReusableMethods.bekleTiklanabilir(paymentPage.termsAndConditionsCheckbox);
    }
    @Then("Rezervasyonu Tamamla butonuna tiklanir")
    public void rezervasyonu_tamamla_butonuna_tiklanir() {
       // Assert.fail("Bilerek hata fırlatıldı - test senaryosu kontrolü");
        ReusableMethods.bekleTiklanabilir(paymentPage.completedRezervation);
        ReusableMethods.bekle(10);
    }
    @Then("Rezervasyonun onaylandigi dogrulanir")
    public void rezervasyonun_onaylandigi_dogrulanir() {

    }
}
