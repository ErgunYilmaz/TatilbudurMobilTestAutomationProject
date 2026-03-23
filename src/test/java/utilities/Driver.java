package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Driver {

    private static AndroidDriver appiumDriver;

    public static AndroidDriver getAndroidDriver() {

        if (appiumDriver == null) {

            try {

                boolean isGitHubActions = "true".equalsIgnoreCase(System.getenv("GITHUB_ACTIONS"));

                URL appiumServerURL = new URL("http://127.0.0.1:4723/");

                UiAutomator2Options options = new UiAutomator2Options();

                options.setAutomationName("UiAutomator2");
                options.setPlatformName("Android");
                options.setNoReset(false);
                options.setAutoGrantPermissions(true);
                options.setNewCommandTimeout(Duration.ofSeconds(300));

                // 🔥 KRİTİK FIX (id locator sorunu için)
                options.setCapability("appium:disableIdLocatorAutocompletion", true);

                if (isGitHubActions) {

                    // CI ortamı
                    options.setDeviceName("Android Emulator");
                    options.setPlatformVersion("11.0");

                    String appPath = System.getProperty("user.dir") + "/Apps/tb.apk";
                    options.setApp(appPath);

                } else {

                    // Local ortam
                    options.setDeviceName("Pixel 4 H");
                    options.setPlatformVersion("10.0");
                    options.setAppPackage("com.mikatur.tatilbudur");
                    options.setAppActivity("com.mikatur.tatilbudur.MainActivity");
                }

                appiumDriver = new AndroidDriver(appiumServerURL, options);

                // implicit wait
                appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

            } catch (MalformedURLException e) {
                throw new RuntimeException("Appium server URL hatalı!", e);
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