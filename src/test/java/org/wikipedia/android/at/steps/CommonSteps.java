package org.wikipedia.android.at.steps;

import com.codeborne.selenide.ex.ElementNotFound;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.wikipedia.android.at.utils.GestureUtils;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.wikipedia.android.at.utils.AppUtils.appId;
import static org.wikipedia.android.at.utils.GestureUtils.Direction.LEFT;
import static org.wikipedia.android.at.utils.GestureUtils.Direction.RIGHT;

public class CommonSteps {

    @Step("Нажимаем кнопку 'Back'")
    public static void pressBackButton() {
        back();
    }

    @Step("Свайпаем влево")
    public static void swipeLeft() {
        GestureUtils.swipe((AppiumDriver) getWebDriver(), LEFT, 0.3);
    }

    @Step("Свайпаем вправо")
    public static void swipeRight() {
        GestureUtils.swipe((AppiumDriver) getWebDriver(), RIGHT, 0.3);
    }

    @Step("Закрываем промо-диалог")
    public static void closePromoDialogIfPresent() {
        try {
            $(appId("dialogContainer")).should(appear, Duration.ofSeconds(2));
            $(appId("closeButton")).click();
        } catch (ElementNotFound e) {
            System.out.println("Promo dialog was not displayed");
        }
    }
}
