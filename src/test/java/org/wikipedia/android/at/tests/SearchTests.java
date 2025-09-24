package org.wikipedia.android.at.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.wikipedia.android.at.screens.ArticleScreen;
import org.wikipedia.android.at.screens.ExploreScreen;
import org.wikipedia.android.at.screens.SearchScreen;

import static org.wikipedia.android.at.steps.CommonSteps.closePromoDialogIfPresent;
import static org.wikipedia.android.at.steps.CommonSteps.pressBackButton;

@DisplayName("Поиск статей")
public class SearchTests extends BaseTest {
    private final ExploreScreen exploreScreen = new ExploreScreen();
    private final SearchScreen searchScreen = new SearchScreen();
    private final ArticleScreen articleScreen = new ArticleScreen();

    @BeforeEach
    void setup() {
        pressBackButton();
    }

    @Test
    @DisplayName("Ввод поискового запроса с проверкой наличия результатов")
    void searchTest() {
        String searchQuery = "Android";

        exploreScreen.clickSearchContainer();

        searchScreen
                .enterSearchQuery(searchQuery)
                .shouldHaveNonEmptySearchResults();
    }

    @Test
    @DisplayName("Открытие статьи из результатов поиска")
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
