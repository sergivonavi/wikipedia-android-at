package org.wikipedia.android.at.drivers;

import com.codeborne.selenide.WebDriverProvider;
import com.google.common.base.Strings;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobilePlatform;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.wikipedia.android.at.config.AppConfig;
import org.wikipedia.android.at.config.DeviceConfig;
import org.wikipedia.android.at.config.RemoteConfig;
import org.wikipedia.android.at.utils.RemoteUtils;
import org.wikipedia.android.at.utils.allure.report.RemoteSession;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class BrowserstackDriver implements WebDriverProvider {
    private static final RemoteConfig remoteConfig = ConfigFactory.create(RemoteConfig.class);
    private static final DeviceConfig deviceConfig = ConfigFactory.create(DeviceConfig.class);
    private static final AppConfig appConfig = ConfigFactory.create(AppConfig.class);

    private static final String BUILD_NAME = String.format(
            "build-%s",
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"))
    );

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        String userName = remoteConfig.username();
        String accessKey = remoteConfig.accessKey();

        if (Strings.isNullOrEmpty(userName) || Strings.isNullOrEmpty(accessKey)) {
            throw new IllegalArgumentException("Username or access key is not set");
        }

        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("userName", userName);
        bstackOptions.put("accessKey", accessKey);
        bstackOptions.put("projectName", remoteConfig.projectName());
        bstackOptions.put("buildName", BUILD_NAME);
        bstackOptions.put("sessionName", RemoteSession.get());

        UiAutomator2Options options = new UiAutomator2Options();
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .setPlatformName(MobilePlatform.ANDROID)
                .setPlatformVersion(deviceConfig.platformVersion())
                .setDeviceName(deviceConfig.deviceName())
                .setApp(appConfig.appId())
                .setAppPackage(appConfig.appPackage())
                .setAppActivity(appConfig.appActivity())
                .setCapability("bstack:options", bstackOptions);

        try {
            String remoteUrl = RemoteUtils.remoteUrl(remoteConfig.domain());
            return new AndroidDriver(URI.create(remoteUrl).toURL(), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
