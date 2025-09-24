package org.wikipedia.android.at.data;

public enum OnboardingScreenData {
    FRAGMENT_1(
            "The Free Encyclopedia\n…in over 300 languages",
            "We’ve found the following on your device:"
    ),
    FRAGMENT_2(
            "New ways to explore",
            "Dive down the Wikipedia rabbit hole with a constantly updating Explore feed.\nCustomize the feed to your interests – whether it’s learning about historical events On this day, or rolling the dice with Random."
    ),
    FRAGMENT_3(
            "Reading lists with sync",
            "You can make reading lists from articles you want to read later, even when you’re offline.\nLogin to your Wikipedia account to sync your reading lists. Join Wikipedia"
    ),
    FRAGMENT_4(
            "Data & Privacy",
            "We believe that you should not have to provide personal information to participate in the free knowledge movement. Usage data collected for this app is anonymous. Learn more about our privacy policy and terms of use."
    );

    private final String primaryText;
    private final String secondaryText;

    OnboardingScreenData(String primaryText, String secondaryText) {
        this.primaryText = primaryText;
        this.secondaryText = secondaryText;
    }

    public String primaryText() {
        return primaryText;
    }

    public String secondaryText() {
        return secondaryText;
    }
}
