package org.wikipedia.android.at.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobilePlatform;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.wikipedia.android.at.config.AppConfig;
import org.wikipedia.android.at.config.DeviceConfig;
import org.wikipedia.android.at.config.RemoteConfig;
import org.wikipedia.android.at.utils.RemoteUtils;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;

public class LocalDriver implements WebDriverProvider {
    private static final RemoteConfig remoteConfig = ConfigFactory.create(RemoteConfig.class);
    private static final DeviceConfig deviceConfig = ConfigFactory.create(DeviceConfig.class);
    private static final AppConfig appConfig = ConfigFactory.create(AppConfig.class);

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .setPlatformName(MobilePlatform.ANDROID)
                .setPlatformVersion(deviceConfig.platformVersion())
                .setDeviceName(deviceConfig.deviceName())
                .setApp(getAppPath())
                .setAppPackage(appConfig.appPackage())
                .setAppActivity(appConfig.appActivity());

        try {
            String remoteUrl = RemoteUtils.remoteUrl(remoteConfig.domain());
            return new AndroidDriver(URI.create(remoteUrl).toURL(), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getAppPath() {
        String appVersion = "app-alpha-universal-release.apk";
        String appUrl = "https://github.com/wikimedia/apps-android-wikipedia/releases/download/latest/" + appVersion;
        String appPath = "src/test/resources/apps/" + appVersion;

        File app = new File(appPath);
        if (!app.exists()) {
            try (InputStream in = URI.create(appUrl).toURL().openStream()) {
                FileUtils.copyInputStreamToFile(in, app);
            } catch (IOException e) {
                throw new RuntimeException("Failed to download application", e);
            }
        }
        return app.getAbsolutePath();
    }
}
