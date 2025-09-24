 package org.wikipedia.android.at.utils;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.wikipedia.android.at.config.AppConfig;

public class AppUtils {
    private static final String PACKAGE = ConfigFactory.create(AppConfig.class).appPackage();

    public static By appId(String id) {
        return By.id(PACKAGE + ":id/" + id);
    }
}
