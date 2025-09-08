package utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

import static io.appium.java_client.AppiumBy.androidUIAutomator;
import static org.openqa.selenium.By.xpath;

public class ReusableMethods {

    private static final Logger logger = LoggerFactory.getLogger(ReusableMethods.class);

    public static void koordinatTiklamaMethodu(int xkoordinati, int ykoordinati, int beklemesuresi){
        logger.info("Koordinat tıklaması: x={}, y={}, bekleme={}ms", xkoordinati, ykoordinati, beklemesuresi);
        TouchAction action=new TouchAction<>(Driver.getAndroidDriver());
        action.press(PointOption.point(xkoordinati,ykoordinati))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(beklemesuresi)))
                .release()
                .perform();
    }

    public static void koordinatKaydirmaMethodu(int startX, int startY, int endX, int endY, int beklemeSuresiMillis) {
        logger.info("Koordinat kaydırma: startX={}, startY={}, endX={}, endY={}, bekleme={}ms", startX, startY, endX, endY, beklemeSuresiMillis);
        TouchAction<?> action = new TouchAction<>(Driver.getAndroidDriver());
        action.press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(beklemeSuresiMillis)))
                .moveTo(PointOption.point(endX, endY))
                .release()
                .perform();
    }

    public static void scrollWithUiScrollableAndClick(String elementText) {
        logger.info("Scroll ve tıklama yapılacak element text: {}", elementText);
        AndroidDriver driver = (AndroidDriver)  Driver.getAndroidDriver();
        driver.findElement(androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + elementText + "\"))"));
        driver.findElement(xpath("//*[@text='" + elementText + "']")).click();
    }

    public static void scrollWithUiScrollable(String elementText){
        logger.info("Scroll yapılacak element text: {}", elementText);
        AndroidDriver driver = (AndroidDriver)  Driver.getAndroidDriver();
        driver.findElement(androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + elementText + "\"))"));
    }

    public static String getScreenshot(String name) throws IOException {
        logger.info("Ekran görüntüsü alınıyor: {}", name);
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot)Driver.getAndroidDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String target = System.getProperty("user.dir") + "/target/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        FileUtils.copyFile(source, finalDestination);
        logger.info("Ekran görüntüsü kaydedildi: {}", target);
        return target;
    }

    public static void ekranKaydirmaMethodu(int xbaslangickoordinati, int ybaslangickoordinati, int beklemesuresi, int xbitiskoordinati, int ybitiskoordinati){
        logger.info("Ekran kaydırılıyor: start({},{}) -> end({},{}) bekleme={}ms", xbaslangickoordinati, ybaslangickoordinati, xbitiskoordinati, ybitiskoordinati, beklemesuresi);
        TouchAction action=new TouchAction<>(Driver.getAndroidDriver());
        action.press(PointOption.point(xbaslangickoordinati,ybaslangickoordinati))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(beklemesuresi)))
                .moveTo(PointOption.point(xbitiskoordinati,ybitiskoordinati))
                .release().perform();
    }

    public static void dikeyKaydirma(RemoteWebDriver driver, double baslangicYuzdesi, double bitisYuzdesi, double sabitYuzde, int sure) {
        logger.info("Dikey kaydırma yapılıyor: baslangicYuzdesi={}, bitisYuzdesi={}, sabitYuzde={}, süre={}ms", baslangicYuzdesi, bitisYuzdesi, sabitYuzde, sure);
        Dimension boyut = driver.manage().window().getSize();
        int sabitNokta = (int) (boyut.width * sabitYuzde);
        int baslangicNoktasi = (int) (boyut.height * baslangicYuzdesi);
        int bitisNoktasi = (int) (boyut.height * bitisYuzdesi);
        PointerInput parmak = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence kaydirma = new Sequence(parmak, 1)
                .addAction(parmak.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), sabitNokta, baslangicNoktasi))
                .addAction(parmak.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(parmak.createPointerMove(Duration.ofMillis(sure), PointerInput.Origin.viewport(), sabitNokta, bitisNoktasi))
                .addAction(parmak.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(kaydirma));
    }

    public static void bekle(int saniye){
        logger.info("Bekleniyor: {} saniye", saniye);
        try {
            Thread.sleep(saniye*1000);
        } catch (InterruptedException e) {
            logger.error("Bekleme esnasında hata oluştu", e);
            throw new RuntimeException(e);
        }
    }

    public static void screenShotElement(String text) throws IOException {
        logger.info("Element ekran görüntüsü alınıyor: {}", text);
        WebElement element = Driver.getAndroidDriver().findElement(xpath("//*[@text='"+text+"']"));
        org.openqa.selenium.Point location = element.getLocation();
        Dimension size = element.getSize();

        File screenshot = Driver.getAndroidDriver().getScreenshotAs(OutputType.FILE);
        BufferedImage fullImage = ImageIO.read(screenshot);
        BufferedImage croppedImage = fullImage.getSubimage(location.getX(), location.getY(), size.getWidth(), size.getHeight());

        File output = new File("kırpılmış_screenshot.png");
        ImageIO.write(croppedImage, "png", output);

        logger.info("Elementin ekran görüntüsü kaydedildi: kırpılmış_screenshot.png");
        Driver.quitAppiumDriver();
    }

    public static byte[] getScreenshot(AppiumDriver driver) {
        logger.info("Byte dizisi olarak ekran görüntüsü alınıyor");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public static String takeScreenshot(AppiumDriver driver, String name) {
        logger.info("Ekran görüntüsü alınıyor: {}", name);
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "target/screenshots/" + name + "_" + UUID.randomUUID() + ".png";
        try {
            FileUtils.copyFile(src, new File(screenshotPath));
            logger.info("Ekran görüntüsü kaydedildi: {}", screenshotPath);
        } catch (IOException e) {
            logger.error("Ekran görüntüsü kaydedilirken hata oluştu", e);
        }
        return screenshotPath;
    }

    public static void selectDateFromToday(int daysFromToday) {
        logger.info("Bugünden itibaren {} gün sonraki tarih seçiliyor", daysFromToday);
        LocalDate targetDate = LocalDate.now().plusDays(daysFromToday);
        int gun = targetDate.getDayOfMonth();
        String ay = targetDate.getMonth().getDisplayName(TextStyle.FULL, new Locale("tr", "TR"));
        int yil = targetDate.getYear();
        String hedefAyYil = ay.toUpperCase(new Locale("tr", "TR")) + " " + yil;

        int maxScroll = 30;
        int scrollCount = 0;
        boolean gunSecildi = false;

        while (scrollCount < maxScroll && !gunSecildi) {
            try {
                logger.info("Scroll denemesi: {}", scrollCount);
                List<WebElement> ayBasliklari = Driver.getAndroidDriver()
                        .findElements(By.className("android.widget.TextView"));

                boolean hedefAyGorunuyor = ayBasliklari.stream()
                        .anyMatch(e -> e.getText().trim().equalsIgnoreCase(hedefAyYil));

                if (hedefAyGorunuyor) {
                    List<WebElement> tumTextViews = Driver.getAndroidDriver()
                            .findElements(By.className("android.widget.TextView"));

                    for (WebElement gunElement : tumTextViews) {
                        String text = gunElement.getText().trim();
                        if (text.equals(String.valueOf(gun))) {
                            int elementY = gunElement.getLocation().getY();
                            int ekranY = Driver.getAndroidDriver().manage().window().getSize().getHeight();

                            if (elementY > ekranY || elementY < 0) {
                                logger.info("Gün elementi ekran dışında, kaydırma yapılacak.");
                                ReusableMethods.dikeyKaydirma(Driver.getAndroidDriver(), 0.7, 0.3, 0.5, 1000);
                                ReusableMethods.bekle(1);
                            }

                            if (gunElement.isDisplayed() && gunElement.isEnabled()) {
                                logger.info("Gün bulundu ve tıklanıyor: {}", gun);
                                gunElement.click();
                                gunSecildi = true;
                                break;
                            }
                        }
                    }

                    if (!gunSecildi) {
                        logger.info("Gün seçilemedi, tekrar kaydırılıyor.");
                        ReusableMethods.dikeyKaydirma(Driver.getAndroidDriver(), 0.7, 0.3, 0.5, 1000);
                        ReusableMethods.bekle(1);
                        scrollCount++;
                    }

                } else {
                    logger.info("Hedef ay '{}' görünmüyor, kaydırılıyor.", hedefAyYil);
                    ReusableMethods.dikeyKaydirma(Driver.getAndroidDriver(), 0.7, 0.3, 0.5, 1000);
                    ReusableMethods.bekle(1);
                    scrollCount++;
                }

            } catch (Exception e) {
                logger.error("Hata oluştu, kaydırma devam ediyor", e);
                scrollCount++;
                ReusableMethods.dikeyKaydirma(Driver.getAndroidDriver(), 0.7, 0.3, 0.5, 1000);
                ReusableMethods.bekle(1);
            }
        }

        if (!gunSecildi) {
            logger.error("Tarih seçilemedi: {} {}", gun, hedefAyYil);
            throw new NoSuchElementException("Tarih seçilemedi: " + gun + " " + hedefAyYil);
        }
    }

    public static void yaz(WebElement element, String text) {
        try {
            logger.info("Elemente yazı yazma işlemi başlatıldı: {} , Yazılacak metin: {}", element, text);
            WebDriverWait wait = new WebDriverWait(Driver.getAndroidDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(text);
            logger.info("Elemente başarıyla yazıldı: {}", text);
        } catch (Exception e) {
            logger.error("Elemente yazı yazma sırasında hata oluştu: {}", e.getMessage(), e);
            throw new RuntimeException("Elemente yazı yazılamadı: " + e.getMessage());
        }
    }

    public static void bekleGorunur(WebElement element) {
        try {
            logger.info("Elementin görünür olması bekleniyor: {}", element);
            WebDriverWait wait = new WebDriverWait(Driver.getAndroidDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));
            logger.info("Element görünür durumda: {}", element);
        } catch (Exception e) {
            logger.error("Element görünür olma beklemesi sırasında hata oluştu: {}", e.getMessage(), e);
            throw new RuntimeException("Element görünür olma beklemesi başarısız: " + e.getMessage());
        }
    }

    public static void bekleTiklanabilir(WebElement element) {
        try {
            logger.info("Elementin tıklanabilir olması bekleniyor: {}", element);
            WebDriverWait wait = new WebDriverWait(Driver.getAndroidDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            logger.info("Element tıklanabilir durumda: {}", element);
        } catch (Exception e) {
            logger.error("Element tıklanabilir olma beklemesi sırasında hata oluştu: {}", e.getMessage(), e);
            throw new RuntimeException("Element tıklanabilir olma beklemesi başarısız: " + e.getMessage());
        }
    }

    public static void textIceriyorMu(WebElement element, String expectedText) {
        try {
            String actualText = element.getText();
            logger.info("Element metni kontrol ediliyor. Beklenen: '{}', Gerçek: '{}'", expectedText, actualText);
            if (!actualText.contains(expectedText)) {
                String hataMesaji = "Beklenen metin bulunamadı. Beklenen: " + expectedText + ", Gerçek: " + actualText;
                logger.error(hataMesaji);
                throw new AssertionError(hataMesaji);
            }
            logger.info("Metin doğrulaması başarılı.");
        } catch (Exception e) {
            logger.error("Metin doğrulaması sırasında hata oluştu: {}", e.getMessage(), e);
            throw e;
        }
    }

    public static boolean sayfadaTextVarMi(String text) {
        try {
            logger.info("Sayfada '{}' metni aranıyor.", text);
            boolean varMi = Driver.getAndroidDriver().getPageSource().contains(text);
            logger.info("Sayfada metin var mı sonucu: {}", varMi);
            return varMi;
        } catch (Exception e) {
            logger.error("Sayfada metin araması sırasında hata oluştu: {}", e.getMessage(), e);
            return false;
        }
    }

}
