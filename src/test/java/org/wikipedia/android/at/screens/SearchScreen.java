package org.wikipedia.android.at.screens;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.wikipedia.android.at.utils.AppUtils.appId;

public class SearchScreen {
    private final SelenideElement searchTextView = $(appId("search_src_text"));

    @Step("Вводим текст {query} в строку поиска")
    public SearchScreen enterSearchQuery(String query) {
        searchTextView.sendKeys(query);
        return this;
    }

    @Step("Проверяем, что список найденных статей не пустой")
    public SearchScreen shouldHaveNonEmptySearchResults() {
        $$(appId("page_list_item_title")).shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Нажимаем на статью с названием {title}")
    public void clickOnArticleWithTitle(String title) {
        $$(appId("page_list_item_title"))
                .findBy(text(title))
                .shouldBe(visible)
                .click();
    }
}
