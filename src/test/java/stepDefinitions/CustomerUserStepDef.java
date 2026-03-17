package stepDefinitions;

import io.cucumber.java.en.Given;
import pages.customerInformationPage;
import utilities.ReusableMethods;

public class CustomerUserStepDef {
    ReusableMethods reusableMethods=new ReusableMethods();
    customerInformationPage customerInformationPage =new customerInformationPage();
    @Given("Erkek radio butonuna tiklanir")
    public void erkek_radio_butonuna_tiklanir() {
        ReusableMethods.scrollWithUiScrollable("+ Ekle");
        reusableMethods.bekleTiklanabilir(customerInformationPage.genderMan);
    }
    @Given("Ad alani {string} ile doldurulur")
    public void ad_alani_ile_doldurulur(String name) {
        reusableMethods.yaz(customerInformationPage.nameOne,name);
    }
    @Given("Soyad alani {string} ile doldurulur")
    public void soyad_alani_ile_doldurulur(String surName) {
        reusableMethods.yaz(customerInformationPage.surNameOne,surName);
    }
    @Given("TC kimlik numarasi {string} girilir")
    public void tc_kimlik_numarasi_girilir(String tcID) {
        reusableMethods.yaz(customerInformationPage.tcKimlikNoInput,tcID);
    }
    @Given("Telefon {string} girilir")
    public void telefon_girilir(String phone) {
        reusableMethods.yaz(customerInformationPage.phoneInput,phone);
    }
    @Given("Eposta {string} girilir")
    public void eposta_girilir(String email) {
        reusableMethods.yaz(customerInformationPage.emailInput,email);
    }
    @Given("Dogum tarihi secim alani acilir")
    public void dogum_tarihi_secim_alani_acilir() {
        reusableMethods.bekleTiklanabilir(customerInformationPage.birthDatePicker);
    }
    @Given("Dogum tarihi picker ekraninda gun {string} ay {string} yil {string} olacak sekilde kaydirilir")
    public void dogum_tarihi_picker_ekraninda_gun_ay_yil_olacak_sekilde_kaydirilir(String string, String string2, String string3) {

    }
    @Given("Dogum tarihi icin Sec butonuna tiklanir")
    public void dogum_tarihi_icin_sec_butonuna_tiklanir() {
        reusableMethods.bekleTiklanabilir(customerInformationPage.birthDateChoose);
    }
    @Given("{int}. kisi ekle butonuna tiklanir")
    public void kisi_ekle_butonuna_tiklanir(Integer int1) {
        ReusableMethods.bekleTiklanabilir(customerInformationPage.secondCustomerTitle);
    }
    @Given("2. kisi icin Kadin radio butonuna tiklanir")
    public void ikinci_kisi_icin_kadin_radio_butonuna_tiklanir() {
        ReusableMethods.bekleTiklanabilir(customerInformationPage.secondCustomerGenderFemale);
    }

    @Given("2. kisi ad alani {string} ile doldurulur")
    public void ikinci_kisi_ad_alani_ile_doldurulur(String ad) {
        ReusableMethods.yaz(customerInformationPage.secondCustomerNameInput, ad);
    }

    @Given("2. kisi soyad alani {string} ile doldurulur")
    public void ikinci_kisi_soyad_alani_ile_doldurulur(String soyad) {
        ReusableMethods.yaz(customerInformationPage.secondCustomerSurnameInput, soyad);
    }

    @Given("2. kisi icin dogum tarihi secim alani acilir")
    public void ikinci_kisi_icin_dogum_tarihi_secim_alani_acilir() {
        ReusableMethods.bekleTiklanabilir(customerInformationPage.secondCustomerBirthDatePicker);
    }

    @Given("2. kisi dogum tarihi picker ekraninda gun {string} ay {string} yil {string} olacak sekilde kaydirilir")
    public void ikinci_kisi_dogum_tarihi_picker_kaydirilir(String gun, String ay, String yil) {

    }

    @Given("2. kisi dogum tarihi icin Sec butonuna tiklanir")
    public void ikinci_kisi_dogum_tarihi_icin_sec_butonuna_tiklanir() {
        ReusableMethods.bekleTiklanabilir(customerInformationPage.birthDateChoose);
    }

    @Given("2. kisi Ekle butonuna tiklanir")
    public void ikinci_kisi_ekle_butonuna_tiklanir() {
        ReusableMethods.bekleTiklanabilir(customerInformationPage.secondCustomerAddButtonOtherPage);
    }

    @Given("Odeme adimina devam edilir")
    public void odeme_adimina_devam_edilir() {
        ReusableMethods.scrollWithUiScrollable("Ödeme Adımına Devam Et");
        ReusableMethods.bekleTiklanabilir(customerInformationPage.continueRezervation);
    }
}
