package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class paymentPage {
    public paymentPage() {
        PageFactory.initElements(new AppiumFieldDecorator(Driver.getAndroidDriver()), this);
    }
    @FindBy(id = "payment-options-method-row-card")
    public WebElement payWithCreditCard;
    @FindBy(id = "card-payment-name-input")
    public WebElement paymentCardUserName;
    @FindBy(id = "card-payment-number-input")
    public WebElement paymentCardNumber;
    @FindBy(id = "card-payment-month-input")
    public WebElement paymentCardMounth;
     @FindBy(xpath = "//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"5\"]")
    public WebElement paymentCardMounthSelect;
     @FindBy(id = "card-payment-year-input")
    public WebElement paymentCardYear;
    @FindBy(xpath = "//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"5\"]")
    public WebElement paymentCardYearSelect;
    @FindBy(id = "card-payment-cvv-input")
    public WebElement cvvInput;
    @FindBy(id = "checkout-payment-checkbox-payment-forms")
    public WebElement termsAndConditionsCheckbox;
    @FindBy(id = "checkout-payment-complete-button")
    public WebElement completedRezervation;





}
