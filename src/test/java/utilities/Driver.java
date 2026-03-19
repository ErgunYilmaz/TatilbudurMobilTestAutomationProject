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
                // GitHub Actions ortamında mıyız?
                boolean isGitHubActions = "true".equalsIgnoreCase(System.getenv("GITHUB_ACTIONS"));

                // Appium 2.x için varsayılan URL (Base path gerekmez)
                URL appiumServerURL = new URL("http://127.0.0.1:4723/");

                UiAutomator2Options options = new UiAutomator2Options();
                options.setAutomationName("UiAutomator2");
                options.setPlatformName("Android");
                options.setNoReset(false);
                options.setAutoGrantPermissions(true);
                options.setNewCommandTimeout(Duration.ofSeconds(300));

                if (isGitHubActions) {
                    // CI Ortamı Ayarları
                    options.setDeviceName("Android Emulator");
                    options.setPlatformVersion("11.0"); // API 30 = Android 11

                    // APK dosyasının proje içindeki yolu
                    String appPath = System.getProperty("user.dir") + "/Apps/tb.apk";
                    options.setApp(appPath);
                } else {
                    // Yerel (Local) Bilgisayar Ayarları
                    options.setDeviceName("Pixel 4 H");
                    options.setPlatformVersion("10.0");
                    options.setAppPackage("com.mikatur.tatilbudur");
                    options.setAppActivity("com.mikatur.tatilbudur.MainActivity");
                }

                appiumDriver = new AndroidDriver(appiumServerURL, options);
                appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

            } catch (MalformedURLException e) {
                throw new RuntimeException("Appium sunucu adresi hatalı!", e);
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