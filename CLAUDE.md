# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

Android UI test automation for the Tatilbudur mobile app (`com.mikatur.tatilbudur`). Stack: Java 17, Maven, Appium java-client 8 (UiAutomator2), Cucumber 7 on JUnit 4, Allure reporting. Feature files, step text, log messages and comments are in Turkish — keep new steps/logs in Turkish to match.

## Commands

Prerequisite for any test run: an Appium server on `http://127.0.0.1:4723/` (`appium`, with `appium driver install uiautomator2`) and a device/emulator visible in `adb devices`.

```
mvn clean test                                        # runs Runner.java with its hardcoded tags (@Tb)
mvn clean test -Dcucumber.filter.tags="@Favori"       # run a single scenario/tag (overrides Runner tags)
allure serve target/allure-results                    # view Allure report
```

- Surefire only includes `**/Runner.java`; all execution goes through Cucumber.
- `Runner.java` has `tags = "@Tb"`, so a plain `mvn test` only runs scenarios tagged `@Tb` (currently `@SiralamaFiltreleri` and `@Rezervasyon`). Most other tags are not in the default run.
- Outputs: `target/cucumber-reports/regression.html`, `target/allure-results`, `target/rerun.txt` (failed scenarios).
- There is no linter or unit test suite. `qodana.yaml` exists for JetBrains Qodana.

## Architecture

Flow: `src/test/resources/Features/*.feature` (Gherkin) → `stepDefinitions/*` → `pages/*` (Page Objects) + `utilities/ReusableMethods`. Cucumber glue is `stepDefinitions`, `utilities`, `hooks`.

- **Driver (`utilities/Driver.java`)**: static singleton `AndroidDriver`, lazily created on first `getAndroidDriver()` call. It branches on the `GITHUB_ACTIONS` env var:
  - local: device "Pixel 4 H", Android 10, launches the already-installed app via appPackage/appActivity
  - CI: Android 11 emulator, installs `Apps/tb.apk`
  
  `noReset=false`, implicit wait 20s, and `disableIdLocatorAutocompletion=true`, which is why `@FindBy(id=...)` uses raw resource ids such as `hotel-list-sort-button`.
- **Lifecycle**: `hooks/AllureHooks` `@After` attaches a screenshot to Allure on failure and then calls `Driver.quitAppiumDriver()`, so every scenario gets a fresh driver/app session.
- **Page Objects (`pages/`)**: lowercase class names (e.g. `hotelListPage`). The constructor calls `PageFactory.initElements(new AppiumFieldDecorator(Driver.getAndroidDriver()), this)`. Dynamic elements are built in methods that use XPath on `android.widget.TextView[@text=...]`. Step def classes instantiate pages as fields, and creating a page starts the driver.
- **ReusableMethods**: shared waits/gestures/screenshots. Watch the names: `bekleTiklanabilir(el)` waits for visibility **and clicks**, `yaz(el, text)` waits, clears and types, and `bekleGorunur` only waits. Scrolling uses UiScrollable or coordinate swipes (`koordinatKaydirmaMethodu`, `dikeyKaydirma`). Date selection uses `selectDateFromToday(n)`.
- **Background steps**: `CommonStepsDef` has the shared background steps. "Cerezler kabul edilir" and "Bildirim izni cikarsa kapatilir" are currently empty stubs.
- **APK**: `Apps/tb.apk` is gitignored and not tracked. CI downloads it from the `apk-v1` release; local runs use the app already installed on the device.

## CI (`.github/workflows/android-appium.yml`)

- Triggers: manual dispatch only (`workflow_dispatch`). The push and daily cron triggers were removed on purpose.
- Steps: downloads the APK from GitHub release tag `apk-v1`, starts Appium, then runs `mvn clean test` inside an API 30 `pixel_4` emulator with screen recording.
- The Maven exit code is saved to `test-exit-code.txt` instead of failing immediately, so video, report and mail steps still run. The last step (`Test sonucunu kontrol et`) fails the job if that code is non-zero or the file is missing. `android-emulator-runner` runs each `script:` line in a separate shell, so shell variables do not carry over between lines; use files for that.
- After the run it generates Allure, publishes the report and video to `gh-pages` under `runs/run-<n>-attempt-<m>/`, and emails a summary to the team via SMTP secrets `EMAIL_USERNAME`/`EMAIL_PASSWORD`.
