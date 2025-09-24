package org.wikipedia.android.at.config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.LoadType.MERGE;

@Config.LoadPolicy(MERGE)
@Config.Sources({
        "system:properties",
        "file:config/app/app.properties",
        "classpath:config/app/app.properties"
})
public interface AppConfig extends Config {

    @Key("app.id")
    @DefaultValue("")
    String appId();

    @Key("app.package")
    String appPackage();

    @Key("app.activity")
    String appActivity();
}
