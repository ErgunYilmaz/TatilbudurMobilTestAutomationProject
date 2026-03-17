package pages;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.List;

public class hotelDetailPage {

    public hotelDetailPage() {
        PageFactory.initElements(new AppiumFieldDecorator(Driver.getAndroidDriver()), this);
    }

    @FindBy(xpath = "//android.widget.TextView[@text='Rezervasyon Yap']")
    public WebElement rezervasyonYapButonu;
}