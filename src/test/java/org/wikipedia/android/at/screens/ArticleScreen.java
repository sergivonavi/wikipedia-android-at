package org.wikipedia.android.at.screens;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.xpath;

public class ArticleScreen {

    @Step("Проверяем, что в статье содержится текст {text}")
    public void shouldHaveText(String text) {
        $(xpath("//android.widget.TextView[@text='" + text + "']")).shouldBe(visible);
    }
}
