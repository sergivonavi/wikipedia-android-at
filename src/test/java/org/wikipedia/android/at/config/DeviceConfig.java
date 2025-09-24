package org.wikipedia.android.at.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "file:config/devices/${host}.${device}.properties",
        "classpath:config/devices/${host}.${device}.properties",
        "classpath:config/devices/${host}.default.properties"
})
public interface DeviceConfig extends Config {

    @Key("device.name")
    String deviceName();

    @Key("platform.version")
    String platformVersion();
}
