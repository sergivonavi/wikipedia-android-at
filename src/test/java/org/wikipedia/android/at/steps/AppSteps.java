package org.wikipedia.android.at.steps;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.wikipedia.android.at.config.AppConfig;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AppSteps {
    private static final String APP_PACKAGE = ConfigFactory.create(AppConfig.class).appPackage();

    @Step("Останавливаем приложение")
    public static void terminateApp() {
        ((AndroidDriver) getWebDriver()).terminateApp(APP_PACKAGE);
    }

    @Step("Запускаем приложение")
    public static void launchApp() {
        ((AndroidDriver) getWebDriver()).activateApp(APP_PACKAGE);
    }

    @Step("Перезапускаем приложение")
    public static void restartApp() {
        terminateApp();
        launchApp();
    }
}
