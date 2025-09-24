package org.wikipedia.android.at.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.wikipedia.android.at.data.OnboardingScreenData;
import org.wikipedia.android.at.screens.ExploreScreen;
import org.wikipedia.android.at.screens.OnboardingScreen;

import static org.wikipedia.android.at.steps.AppSteps.restartApp;
import static org.wikipedia.android.at.steps.CommonSteps.closePromoDialogIfPresent;
import static org.wikipedia.android.at.steps.CommonSteps.pressBackButton;
import static org.wikipedia.android.at.steps.CommonSteps.swipeLeft;
import static org.wikipedia.android.at.steps.CommonSteps.swipeRight;

@DisplayName("Онбординг")
public class OnboardingTests extends BaseTest {
    private final OnboardingScreen onboardingScreen = new OnboardingScreen();
    private final ExploreScreen exploreScreen = new ExploreScreen();

    @Test
    @DisplayName("Проверка содержимого всех фрагментов онбординга")
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

    @Test
    @DisplayName("Переход между фрагментами онбординга свайпами")
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

    @Test
    @DisplayName("Нажатие кнопки \"Back\" закрывает онбординг")
    void backButtonClosesOnboardingTest() {
        onboardingScreen.shouldHaveTexts(OnboardingScreenData.FRAGMENT_1);
        pressBackButton();

        exploreScreen.shouldHaveToolbarLogoDisplayed();
    }

    @Test
    @DisplayName("Нажатие кнопки \"Skip\" закрывает онбординг")
    void skipButtonClosesOnboardingTest() {
        onboardingScreen
                .shouldHaveTexts(OnboardingScreenData.FRAGMENT_1)
                .clickSkipButton();

        exploreScreen.shouldHaveToolbarLogoDisplayed();
    }

    @Test
    @DisplayName("Нажатие кнопки \"Get started\" закрывает онбординг")
    void doneButtonClosesOnboardingTest() {
        onboardingScreen
                .clickContinueButton()
                .clickContinueButton()
                .clickContinueButton()
                .shouldHaveDoneButton()
                .clickDoneButton();

        exploreScreen.shouldHaveToolbarLogoDisplayed();
    }

    @Test
    @DisplayName("Онбординг не открывается после пропуска при повторном запуске приложения")
    void skipOnboardingPersistsTest() {
        onboardingScreen.clickSkipButton();
        exploreScreen.shouldHaveToolbarLogoDisplayed();

        restartApp();

        closePromoDialogIfPresent();
        exploreScreen.shouldHaveToolbarLogoDisplayed();
    }

    @Test
    @DisplayName("Онбординг не открывается после завершения при повторном запуске приложения")
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
