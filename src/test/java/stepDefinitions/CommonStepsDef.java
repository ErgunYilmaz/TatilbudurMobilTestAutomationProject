package stepDefinitions;

import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import utilities.Driver;
import utilities.ReusableMethods;

public class CommonStepsDef {

    @Given("Cerezler kabul edilir")
    public void cerezler_kabul_edilir() {
        ReusableMethods.varsaTikla(By.xpath("//*[@text='Kabul Et']"), 3);
        ReusableMethods.varsaTikla(By.xpath("//*[@text='Tümünü Kabul Et']"), 3);
    }

    @Given("Bildirim izni cikarsa kapatilir")
    public void bildirim_izni_cikarsa_kapatilir() {
        ReusableMethods.varsaTikla(By.id("com.android.permissioncontroller:id/permission_deny_button"), 3);
        ReusableMethods.varsaTikla(By.id("com.android.permissioncontroller:id/permission_allow_button"), 3);
        ReusableMethods.varsaTikla(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"), 3);
        ReusableMethods.varsaTikla(By.xpath("//*[@text='Daha Sonra']"), 3);
        ReusableMethods.varsaTikla(By.xpath("//*[@text='Kapat']"), 3);
    }

    @Given("Uygulamanin acildigi kontrol edilir ve gerekli izinler verilir")
    public void uygulamanin_acildigi_kontrol_edilir_ve_gerekli_izinler_verilir() {

        Driver.getAndroidDriver();

        // biraz bekle (CI için kritik)
        ReusableMethods.bekle(5);

        // sistem izinleri
        ReusableMethods.varsaTikla(By.id("com.android.permissioncontroller:id/permission_allow_button"), 3);
        ReusableMethods.varsaTikla(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"), 3);
        ReusableMethods.varsaTikla(By.id("com.android.permissioncontroller:id/permission_deny_button"), 3);

        // app popup
        ReusableMethods.varsaTikla(By.xpath("//*[@text='Kapat']"), 3);
        ReusableMethods.varsaTikla(By.xpath("//*[@text='Daha Sonra']"), 3);
        ReusableMethods.varsaTikla(By.xpath("//*[@text='Tamam']"), 3);

        // ANA EKRAN DOĞRULAMA (EN KRİTİK)
        ReusableMethods.bekleTiklanabilir(By.id("hotel-search-location-input"));
    }
}