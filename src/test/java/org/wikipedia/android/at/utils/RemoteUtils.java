package org.wikipedia.android.at.utils;

import org.openqa.selenium.remote.SessionId;

import static io.restassured.RestAssured.given;

public class RemoteUtils {

    public static String remoteUrl(String domain) {
        String protocol = domain.toLowerCase().startsWith("localhost") || domain.toLowerCase().startsWith("127.0")
                ? "http"
                : "https";
        return String.format("%s://%s/wd/hub", protocol, domain);
    }

    public static String videoUrl(String username, String accessKey, SessionId sessionId) {
        String url = String.format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .auth().basic(username, accessKey)
                .get(url)
                .then()
                .statusCode(200)
                .extract().path("automation_session.video_url");
    }
}
