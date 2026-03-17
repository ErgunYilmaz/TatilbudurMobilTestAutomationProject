package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class customerInformationPage {
    public customerInformationPage(){
        PageFactory.initElements(new AppiumFieldDecorator(Driver.getAndroidDriver()), this);
    }
    @FindBy(xpath = "//android.widget.TextView[@text=\"Erkek\"]")
    public WebElement genderMan;
    @FindBy(id = "customers-info-name")
    public WebElement nameOne;
    @FindBy(id = "customers-info-surname")
    public WebElement surNameOne;
    // TC Kimlik No
    @FindBy(xpath = "//android.widget.EditText[@text='TC Kimlik No*']")
    public WebElement tcKimlikNoInput;

    // T.C. vatandaşı değilim switch
    @FindBy(id = "customers-info-citizen-switch")
    public WebElement citizenSwitch;

    // Telefon alanı
    @FindBy(id = "countryPickerPhoneInput")
    public WebElement phoneInput;

    // E-posta
    @FindBy(id = "customers-info-email-input")
    public WebElement emailInput;

    // Doğum tarihi picker alanı
    @FindBy(id = "customers-info-birthdate-picker")
    public WebElement birthDatePicker;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Seç\"]")
    public WebElement birthDateChoose;

    // 2. kişi alanı
    @FindBy(xpath = "//*[@text='2. Kişi']")
    public WebElement secondCustomerTitle;

    // 2. kişi + Ekle
    @AndroidFindBy(accessibility = "+ Ekle")
    public WebElement secondCustomerAddButton;

    // Geri butonu
    @AndroidFindBy(accessibility = "checkout-header-back-button")
    public WebElement backButton;

    // 2. kişi sayfa başlığı
    @AndroidFindBy(id = "add-customer-navigation-bar-title")
    public WebElement secondCustomerPageTitle;

    // 2. kişi geri butonu
    @AndroidFindBy(accessibility = "add-customer-navigation-bar-back-button")
    public WebElement secondCustomerBackButton;

    // 2. kişi kadın radio
    @AndroidFindBy(accessibility = "add-customer-radio-button-female")
    public WebElement secondCustomerGenderFemale;

    // 2. kişi erkek radio
    @AndroidFindBy(accessibility = "add-customer-radio-button-male")
    public WebElement secondCustomerGenderMale;

    // 2. kişi ad
    @AndroidFindBy(id = "add-customer-name-input")
    public WebElement secondCustomerNameInput;

    // 2. kişi soyad
    @AndroidFindBy(id = "add-customer-surname-input")
    public WebElement secondCustomerSurnameInput;

    // 2. kişi doğum tarihi
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"add-customer-birthdate-picker\"]/android.view.ViewGroup")
    public WebElement secondCustomerBirthDatePicker;

    // 2. kişi ekle butonu
    @AndroidFindBy(id = "add-customer-add-button")
    public WebElement secondCustomerAddButtonOtherPage;

    //Ödemeye devam et
    @FindBy(xpath="//android.widget.TextView[@text=\"Ödeme Adımına Devam Et\"]")
    public WebElement continueRezervation;

}
