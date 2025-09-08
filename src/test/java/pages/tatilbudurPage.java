package pages;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class tatilbudurPage {
    public tatilbudurPage(){
        PageFactory.initElements(new AppiumFieldDecorator(Driver.getAndroidDriver()),this);
    }

    @FindBy(xpath = "//*[@resource-id='hotel-search-location-input']")
    public WebElement otelSehirBolgeveyaTemaAdiButonu;

    @FindBy(xpath = "//*[@text='Otel, Şehir, Bölge veya Tema adı']")
    public WebElement otelSehirBolgeveyaTemaAdi2Butonu;

    @FindBy(xpath = "//*[@text = 'Antalya Otelleri']")
    public WebElement antalyaBolgeleriButonu;

    @FindBy(xpath = "(//*[@class='android.view.ViewGroup'])[25]")
    public WebElement datePickerButonu;

    @FindBy(xpath = "//*[@text='Yurtiçi Oteller']")
    public WebElement yurticiButonu;

    @FindBy(xpath = "(//*[@text='Otel Ara'])[2]")
    public WebElement otelAraButonu;

    @FindBy(xpath = "//*[@text='Sıralama']")
    public WebElement siralamaButonu;

    @FindBy(xpath = "(//*[@class='android.view.ViewGroup'])[15]")
    public WebElement fiyatArtanButonu;

    @FindBy(xpath = "(//*[@class='android.view.ViewGroup'])[29]")
    public WebElement sonucuGosterButonu;


}
