package org.wikipedia.android.at.screens;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.wikipedia.android.at.utils.AppUtils.appId;

public class ExploreScreen {
    private final SelenideElement toolbarLogo = $(appId("main_toolbar_wordmark"));
    private final SelenideElement searchContainer = $(appId("search_container"));

    @Step("Нажимаем на поле поиска")
    public void clickSearchContainer() {
        searchContainer.click();
    }

    @Step("Проверяем, что логотип в тулбаре отображается")
    public void shouldHaveToolbarLogoDisplayed() {
        toolbarLogo.shouldBe(visible);
    }
}
