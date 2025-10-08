package org.wikipedia.android.at.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.wikipedia.android.at.screens.ArticleScreen;
import org.wikipedia.android.at.screens.ExploreScreen;
import org.wikipedia.android.at.screens.SearchScreen;
import org.wikipedia.android.at.utils.allure.annotations.Layer;

import static org.wikipedia.android.at.steps.CommonSteps.closePromoDialogIfPresent;
import static org.wikipedia.android.at.steps.CommonSteps.pressBackButton;

@Owner("sergivonavi")
@Layer("mobile")
@Tag("mobile")
@Epic("Android-приложение")
@Feature("Поиск")
@Story("Работа со статьями")
@DisplayName("Поиск статей")
public class SearchTests extends BaseTest {
    private final ExploreScreen exploreScreen = new ExploreScreen();
    private final SearchScreen searchScreen = new SearchScreen();
    private final ArticleScreen articleScreen = new ArticleScreen();

    @BeforeEach
    void setup() {
        pressBackButton();
    }

    @Tags({@Tag("regress"), @Tag("smoke")})
    @Test
    @DisplayName("Ввод поискового запроса с проверкой наличия результатов")
    @Severity(SeverityLevel.BLOCKER)
    void searchTest() {
        String searchQuery = "Android";

        exploreScreen.clickSearchContainer();

        searchScreen
                .enterSearchQuery(searchQuery)
                .shouldHaveNonEmptySearchResults();
    }

    @Tags({@Tag("regress"), @Tag("smoke")})
    @Test
    @DisplayName("Открытие статьи из результатов поиска")
    @Severity(SeverityLevel.CRITICAL)
    void openArticleTest() {
        String searchQuery = "Android";
        String articleTitle = "Android (operating system)";

        exploreScreen.clickSearchContainer();

        searchScreen
                .enterSearchQuery(searchQuery)
                .shouldHaveNonEmptySearchResults()
                .clickOnArticleWithTitle(articleTitle);

        closePromoDialogIfPresent();
        articleScreen.shouldHaveText(articleTitle);
    }
}
