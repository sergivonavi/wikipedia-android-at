package org.wikipedia.android.at.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.wikipedia.android.at.data.OnboardingScreenData;
import org.wikipedia.android.at.screens.ExploreScreen;
import org.wikipedia.android.at.screens.OnboardingScreen;
import org.wikipedia.android.at.utils.allure.annotations.Layer;

import static org.wikipedia.android.at.steps.AppSteps.restartApp;
import static org.wikipedia.android.at.steps.CommonSteps.closePromoDialogIfPresent;
import static org.wikipedia.android.at.steps.CommonSteps.pressBackButton;
import static org.wikipedia.android.at.steps.CommonSteps.swipeLeft;
import static org.wikipedia.android.at.steps.CommonSteps.swipeRight;

@Owner("sergivonavi")
@Layer("mobile")
@Tag("mobile")
@Epic("Android-приложение")
@Feature("Онбординг")
@Story("Первый запуск приложения")
@DisplayName("Онбординг")
public class OnboardingTests extends BaseTest {
    private final OnboardingScreen onboardingScreen = new OnboardingScreen();
    private final ExploreScreen exploreScreen = new ExploreScreen();

    @Tag("regress")
    @Test
    @DisplayName("Проверка содержимого всех фрагментов онбординга")
    @Severity(SeverityLevel.NORMAL)
    void allFragmentsContentsTest() {
        onboardingScreen
                .shouldHaveContinueButton()
                .shouldHaveSkipButton()
                .shouldHaveImage()
                .shouldHaveTexts(OnboardingScreenData.FRAGMENT_1)
                .shouldHaveNonEmptyLanguageList()
                .shouldHaveAddLanguageButton()
                .clickContinueButton()
                .shouldHaveImage()
                .shouldHaveTexts(OnboardingScreenData.FRAGMENT_2)
                .clickContinueButton()
                .shouldHaveImage()
                .shouldHaveTexts(OnboardingScreenData.FRAGMENT_3)
                .clickContinueButton()
                .shouldHaveImage()
                .shouldHaveTexts(OnboardingScreenData.FRAGMENT_4)
                .shouldHaveDoneButton()
                .shouldNotHaveSkipButton()
                .shouldNotHaveContinueButton();
    }

    @Tag("regress")
    @Test
    @DisplayName("Переход между фрагментами онбординга свайпами")
    @Severity(SeverityLevel.MINOR)
    void swipeNavigationTest() {
        onboardingScreen.shouldHaveTexts(OnboardingScreenData.FRAGMENT_1);
        swipeRight();
        onboardingScreen.shouldHaveTexts(OnboardingScreenData.FRAGMENT_1);
        swipeLeft();
        onboardingScreen.shouldHaveTexts(OnboardingScreenData.FRAGMENT_2);
        swipeLeft();
        onboardingScreen.shouldHaveTexts(OnboardingScreenData.FRAGMENT_3);
        swipeLeft();
        onboardingScreen.shouldHaveTexts(OnboardingScreenData.FRAGMENT_4);
        swipeLeft();
        onboardingScreen.shouldHaveTexts(OnboardingScreenData.FRAGMENT_4);
        swipeRight();
        onboardingScreen.shouldHaveTexts(OnboardingScreenData.FRAGMENT_3);
        swipeRight();
        onboardingScreen.shouldHaveTexts(OnboardingScreenData.FRAGMENT_2);
        swipeRight();
        onboardingScreen.shouldHaveTexts(OnboardingScreenData.FRAGMENT_1);
    }

    @Tags({@Tag("regress"), @Tag("smoke")})
    @Test
    @DisplayName("Нажатие кнопки \"Back\" закрывает онбординг")
    @Severity(SeverityLevel.CRITICAL)
    void backButtonClosesOnboardingTest() {
        onboardingScreen.shouldHaveTexts(OnboardingScreenData.FRAGMENT_1);
        pressBackButton();

        exploreScreen.shouldHaveToolbarLogoDisplayed();
    }

    @Tags({@Tag("regress"), @Tag("smoke")})
    @Test
    @DisplayName("Нажатие кнопки \"Skip\" закрывает онбординг")
    @Severity(SeverityLevel.CRITICAL)
    void skipButtonClosesOnboardingTest() {
        onboardingScreen
                .shouldHaveTexts(OnboardingScreenData.FRAGMENT_1)
                .clickSkipButton();

        exploreScreen.shouldHaveToolbarLogoDisplayed();
    }

    @Tags({@Tag("regress"), @Tag("smoke")})
    @Test
    @DisplayName("Нажатие кнопки \"Get started\" закрывает онбординг")
    @Severity(SeverityLevel.BLOCKER)
    void doneButtonClosesOnboardingTest() {
        onboardingScreen
                .clickContinueButton()
                .clickContinueButton()
                .clickContinueButton()
                .shouldHaveDoneButton()
                .clickDoneButton();

        exploreScreen.shouldHaveToolbarLogoDisplayed();
    }

    @Tag("regress")
    @Test
    @DisplayName("Онбординг не открывается после пропуска при повторном запуске приложения")
    @Severity(SeverityLevel.NORMAL)
    void skipOnboardingPersistsTest() {
        onboardingScreen.clickSkipButton();
        exploreScreen.shouldHaveToolbarLogoDisplayed();

        restartApp();

        closePromoDialogIfPresent();
        exploreScreen.shouldHaveToolbarLogoDisplayed();
    }

    @Tag("regress")
    @Test
    @DisplayName("Онбординг не открывается после завершения при повторном запуске приложения")
    @Severity(SeverityLevel.NORMAL)
    void finishOnboardingPersistsTest() {
        onboardingScreen
                .clickContinueButton()
                .clickContinueButton()
                .clickContinueButton()
                .clickDoneButton();
        exploreScreen.shouldHaveToolbarLogoDisplayed();

        restartApp();

        closePromoDialogIfPresent();
        exploreScreen.shouldHaveToolbarLogoDisplayed();
    }
}
