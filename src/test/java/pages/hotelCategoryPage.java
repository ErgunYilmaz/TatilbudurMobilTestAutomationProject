package pages;

import io.appium.java_client.pagefactory.AppiumElementLocatorFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class hotelCategoryPage {
    public  hotelCategoryPage() {
        PageFactory.initElements(new AppiumFieldDecorator(Driver.getAndroidDriver()), this);
    }
    @FindBy(id = "hotel-search-modal-input")
    public WebElement hotelSearchModal;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Antalya Otelleri\"]")
    public WebElement clickByContent;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Yurtiçi Oteller\"]")
    public WebElement searchByYurticiCategoris;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Kıbrıs Otelleri\"]")
    public WebElement searchByKibrisOtelCategoris;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Termal Oteller\"]")
    public WebElement searchByTermalOtelCategoris;

}
