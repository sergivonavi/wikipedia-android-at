package org.wikipedia.android.at.config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.LoadType.MERGE;

@Config.LoadPolicy(MERGE)
@Config.Sources({
        "system:properties",
        "file:config/remote/${host}.properties",
        "classpath:config/remote/${host}.properties"
})
public interface RemoteConfig extends Config {

    @Key("remote.isRemote")
    boolean isRemote();

    @Key("remote.domain")
    String domain();

    @Key("remote.username")
    @DefaultValue("")
    String username();

    @Key("remote.access_key")
    @DefaultValue("")
    String accessKey();

    @Key("project.name")
    String projectName();
}
