package org.wikipedia.android.at.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.NoSuchSessionException;
import org.wikipedia.android.at.config.RemoteConfig;
import org.wikipedia.android.at.utils.RemoteUtils;
import org.wikipedia.android.at.utils.report.AllureAttachments;
import org.wikipedia.android.at.utils.report.RemoteSession;

import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public class BaseTest {
    private static final RemoteConfig remoteConfig = ConfigFactory.create(RemoteConfig.class);

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void beforeEach(TestInfo testInfo) {
        RemoteSession.set(testInfo.getDisplayName());

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Selenide.open();
    }

    @AfterEach
    void afterEach() {
        RemoteSession.clear();

        if (hasWebDriverStarted()) {
            try {
                attachArtifacts();
            } finally {
                Selenide.closeWebDriver();
            }
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void attachArtifacts() {
        try {
            AllureAttachments.attachScreenshot("Final state");
            if (remoteConfig.isRemote()) {
                String videoUrl = RemoteUtils.videoUrl(
                        remoteConfig.username(),
                        remoteConfig.accessKey(),
                        Selenide.sessionId()
                );
                AllureAttachments.attachVideo(videoUrl);
            }
            AllureAttachments.attachPageSource();
        } catch (NoSuchSessionException e) {
            System.out.println("WebDriver session already closed, skipping artifacts");
        }
    }
}
