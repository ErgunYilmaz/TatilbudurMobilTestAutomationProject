package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class tatilbudurPage {
    public tatilbudurPage(){
        PageFactory.initElements(new AppiumFieldDecorator(Driver.getAndroidDriver()),this);
    }
    @FindBy(xpath ="//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[3]")
    public  WebElement registerLabel;

    @FindBy(id = "hotel-search-location-input")
    public WebElement hotelSearchlocation;

    @FindBy(id = "hotel-search-date-input")
    public WebElement hotelSearchDate;

    @FindBy(id ="calendar-apply-button")
    public WebElement applyDate;

    @FindBy(id ="hotel-search-customer-input")
    public  WebElement hotelSearchCustomer;

    @FindBy(id = "customer-number-apply-button")
    public WebElement customerNumberApplyButton;

    @FindBy(id = "hotel-search-button")
    public WebElement hotelSearch;

    @FindBy(xpath = "//*[@text = 'Antalya Otelleri']")
    public WebElement antalyaBolgeleriButonu;

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
