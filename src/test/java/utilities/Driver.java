package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Driver {

    private static AndroidDriver appiumDriver;
    private static IOSDriver iosDriver;

    public static AndroidDriver getAndroidDriver() {
        if (appiumDriver == null) {
            try {
                URL appiumServerURL = new URL("http://127.0.0.1:4723");

                UiAutomator2Options options = new UiAutomator2Options();
                options.setAutomationName("UiAutomator2");
                options.setPlatformName("Android");
                options.setNoReset(false);
                options.setAutoGrantPermissions(true);
                options.setNewCommandTimeout(Duration.ofSeconds(300));
                options.setCapability("disableIdLocatorAutocompletion", true);

                boolean isGitHubActions = "true".equalsIgnoreCase(System.getenv("GITHUB_ACTIONS"));

                if (isGitHubActions) {
                    options.setDeviceName("Android Emulator");
                    options.setPlatformVersion("11.0");

                    // APK'yı direkt projeden al
                    String appPath = System.getProperty("user.dir") + "/Apps/tb.apk";
                    options.setApp(appPath);

                } else {
                    options.setDeviceName("Pixel 4 H");
                    options.setPlatformVersion("10.0");

                    options.setAppPackage("com.mikatur.tatilbudur");
                    options.setAppActivity("com.mikatur.tatilbudur.MainActivity");
                }

                if (ConfigReader.getProperty("platformName").equalsIgnoreCase("Android")) {
                    appiumDriver = new AndroidDriver(appiumServerURL, options);
                    appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                } else {
                    iosDriver = new IOSDriver(appiumServerURL, options);
                    iosDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                    throw new UnsupportedOperationException("Cihaz IOS");
                }

            } catch (MalformedURLException e) {
                throw new RuntimeException("Appium server URL hatalı: " + e.getMessage(), e);
            }
        }
        return appiumDriver;
    }

    public static void quitAppiumDriver() {
        if (appiumDriver != null) {
            appiumDriver.quit();
            appiumDriver = null;
        }
    }
}