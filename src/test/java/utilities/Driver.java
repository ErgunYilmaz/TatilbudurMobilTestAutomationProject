package utilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {
    private static AndroidDriver appiumDriver;
    private static IOSDriver iosDriver;
    public static AndroidDriver getAndroidDriver()  {
        URL appiumServerURL = null;
        try {
            appiumServerURL = new URL("http://127.0.0.1:4723/"); //bu kısımda /wd/hub kısmona appium 2.0 da gerek yok.
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (appiumDriver == null) {
            UiAutomator2Options options=new UiAutomator2Options();
            options.
                    setDeviceName("Pixel 4 H")
                    .setPlatformName("Android")
                    .setPlatformVersion("10.0")
                   // setDeviceName("Redmi Note 13")
                   // .setPlatformName("Android")
                   // .setPlatformVersion("15.0")
                    .setAutomationName("UiAutomator2")
                   //.setApp("C:\\Users\\ergun.yilmaz\\IdeaProjects\\TatilbudurMobilProject\\Apps\\v82.apk")
                    .setAppPackage("com.mikatur.tatilbudur")
                    .setAppActivity("com.mikatur.tatilbudur.MainActivity")
                    .setNoReset(false);
            options.setCapability("disableIdLocatorAutocompletion", true);
            if (ConfigReader.getProperty("platformName").equals("Android")) {
                assert appiumServerURL != null;
                appiumDriver = new AndroidDriver(appiumServerURL,options);
                appiumDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            }else {
                assert appiumServerURL != null;
                iosDriver = new IOSDriver(appiumServerURL,options);
                iosDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                throw new UnsupportedOperationException("Cihaz IOS");
            }
        }
        return appiumDriver;
    }
    public static void quitAppiumDriver(){
        if (appiumDriver != null) {
            appiumDriver.quit();
            appiumDriver = null;
        }
    }
}
