package org.wikipedia.android.at.screens;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.wikipedia.android.at.data.OnboardingScreenData;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.wikipedia.android.at.utils.AppUtils.appId;

public class OnboardingScreen {
    private static final String continueButtonText = "Continue";
    private static final String skipButtonText = "Skip";
    private static final String doneButtonText = "Get started";
    private static final String addLanguageButtonText = "Add or edit languages";

    private final SelenideElement continueButton = $(appId("fragment_onboarding_forward_button"));
    private final SelenideElement skipButton = $(appId("fragment_onboarding_skip_button"));
    private final SelenideElement doneButton = $(appId("fragment_onboarding_done_button"));
    private final SelenideElement imageViewCentered = $(appId("imageViewCentered"));
    private final SelenideElement primaryTextView = $(appId("primaryTextView"));
    private final SelenideElement secondaryTextView = $(appId("secondaryTextView"));
    private final SelenideElement addLanguageButton = $(appId("addLanguageButton"));

    @Step("Нажимаем кнопку 'Continue'")
    public OnboardingScreen clickContinueButton() {
        continueButton.click();
        return this;
    }

    @Step("Нажимаем кнопку 'Skip'")
    public void clickSkipButton() {
        skipButton.click();
    }

    @Step("Нажимаем кнопку 'Done'")
    public void clickDoneButton() {
        doneButton.click();
    }

    @Step("Проверяем, что кнопка 'Continue' отображается")
    public OnboardingScreen shouldHaveContinueButton() {
        continueButton
                .shouldBe(visible)
                .shouldHave(text(continueButtonText));
        return this;
    }

    @Step("Проверяем, что кнопка 'Continue' не отображается")
    public void shouldNotHaveContinueButton() {
        continueButton.shouldNotBe(visible);
    }

    @Step("Проверяем, что кнопка 'Skip' отображается")
    public OnboardingScreen shouldHaveSkipButton() {
        skipButton
                .shouldBe(visible)
                .shouldHave(text(skipButtonText));
        return this;
    }

    @Step("Проверяем, что кнопка 'Skip' не отображается")
    public OnboardingScreen shouldNotHaveSkipButton() {
        skipButton.shouldNotBe(visible);
        return this;
    }

    @Step("Проверяем, что кнопка 'Done' отображается")
    public OnboardingScreen shouldHaveDoneButton() {
        doneButton
                .shouldBe(visible)
                .shouldHave(text(doneButtonText));
        return this;
    }

    @Step("Проверяем, что во фрагменте отображается корректный текст")
    public OnboardingScreen shouldHaveTexts(OnboardingScreenData fragment) {
        primaryTextView
                .shouldBe(visible)
                .shouldHave(Condition.text(fragment.primaryText()));
        secondaryTextView
                .shouldBe(visible)
                .shouldHave(Condition.text(fragment.secondaryText()));
        return this;
    }

    @Step("Проверяем, что во фрагменте отображается картинка")
    public OnboardingScreen shouldHaveImage() {
        imageViewCentered.shouldBe(visible);
        return this;
    }

    @Step("Проверяем, что кнопка 'Add or edit languages' отображается")
    public OnboardingScreen shouldHaveAddLanguageButton() {
        addLanguageButton
                .shouldBe(visible)
                .shouldHave(Condition.text(addLanguageButtonText));
        return this;
    }

    @Step("Проверяем, что список языков не пустой")
    public OnboardingScreen shouldHaveNonEmptyLanguageList() {
        $$(appId("option_label")).shouldHave(sizeGreaterThan(0));
        return this;
    }
}
