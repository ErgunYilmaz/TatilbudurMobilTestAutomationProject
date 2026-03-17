package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.*;

import static utilities.ReusableMethods.logger;

public class hotelListPage {

    // Constructor (yapıcı metod): Bu sınıf new'lendiğinde ilk çalışan blok burasıdır.
    public hotelListPage(){
        // Sayfadaki @FindBy ile tanımlanan elementleri aktif hale getirir.
        // Yani bu sınıfta bulunan tüm elementleri Appium driver’a tanıtır.
        PageFactory.initElements(
                // Appium’un özel field decorator’ı, mobil elementleri (MobileElement) tanır.
                new AppiumFieldDecorator(
                        // AndroidDriver nesnesini alır (Driver sınıfından gelir)
                        Driver.getAndroidDriver()
                ),
                // Bu sınıfın (hotelListPage) içindeki elementleri başlatır.
                this
        );
    }

    @FindBy(id="hotel-list-sort-button")
    public WebElement hotelListSortButton;

    // Dinamik olarak siralama filtresine ulaşmak için metot
    public WebElement getSiralamaFiltresi(String filtreAdi) {
        // filtreAdi: "Fiyat (Artan)" gibi feature’dan gelen string
        String xpath = "//android.widget.TextView[@text='" + filtreAdi + "']";
        return Driver.getAndroidDriver().findElement(By.xpath(xpath));
    }

    @FindBy(id ="sort-apply-button")
    public WebElement sortApplyButton;

    public List<Double> getSiralamaDegerleriDouble(String filtreAdi) {

        AppiumDriver driver = Driver.getAndroidDriver();

        List<Double> resultValues = new ArrayList<>();
        Set<String> processedHotels = new HashSet<>();

        String nameXpath = "//*[contains(@resource-id,'hotel-name')]";

        String valueXpath =
                "//*[(" +
                        "contains(@resource-id,'discount-price') or " +
                        "contains(@resource-id,'undefined-discount-price')" +
                        ") and not(contains(@resource-id,'world-card'))]";

        if (filtreAdi.contains("Puan")) {
            valueXpath = "//android.widget.TextView[contains(@text,'/10')]";
        } else if (filtreAdi.contains("İndirim") || filtreAdi.contains("Discount")) {
            valueXpath = "//*[contains(@resource-id,'discount-percentage')]";
        }

        int maxScroll = 25;
        int noNewDataCount = 0;
        int maxNoNewData = 3;

        for (int i = 0; i < maxScroll; i++) {

            List<WebElement> nameElements = driver.findElements(By.xpath(nameXpath));
            List<WebElement> valueElements = driver.findElements(By.xpath(valueXpath));

            int loopSize = Math.min(nameElements.size(), valueElements.size());
            int addedThisScroll = 0;

            for (int k = 0; k < loopSize; k++) {
                try {
                    String otelAdi = nameElements.get(k).getText();

                    // 🔒 OTEL BAZLI KİLİT
                    if (processedHotels.contains(otelAdi)) continue;

                    String rawValue = valueElements.get(k).getText();

                    String cleanValue = rawValue
                            .replace("₺", "")
                            .replace("TL", "").replace("tl", "")
                            .replace("T", "").replace("t", "")
                            .replace("%", "")
                            .replace("/10", "")
                            .replace(".", "")
                            .replace(",", ".")
                            .replaceAll("[^0-9.]", "")
                            .trim();

                    if (!cleanValue.isEmpty()) {
                        double parsedValue = Double.parseDouble(cleanValue);
                        resultValues.add(parsedValue);
                        processedHotels.add(otelAdi);
                        addedThisScroll++;

                        System.out.println("Eklendi: " + otelAdi + " -> " + parsedValue);
                    }

                } catch (Exception e) {
                    System.out.println("Atlandı: " + e.getMessage());
                }
            }

            if (addedThisScroll == 0) {
                noNewDataCount++;
            } else {
                noNewDataCount = 0;
            }

            if (noNewDataCount >= maxNoNewData) {
                System.out.println("Yeni otel gelmedi, durduruldu.");
                break;
            }

            // 🔥 DAHA DERİN SCROLL
            ReusableMethods.dikeyKaydirma(driver, 0.80, 0.10, 0.6, 3500);
            ReusableMethods.bekle(1);
        }

        System.out.println("TOPLAM OTEL: " + processedHotels.size());
        System.out.println("DEĞERLER: " + resultValues);

        return resultValues;


    }

    @FindBy(id ="hotel-card-0")
    public WebElement hotelCardZero;








}
