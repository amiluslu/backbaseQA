package com.amilus.backbaseqa.pages;

import com.amilus.backbaseqa.base.BasePage;
import com.amilus.backbaseqa.base.Listener;
import org.openqa.selenium.By;

import java.util.logging.Logger;

public class SettingsPage extends BasePage {

    Logger log = Logger.getLogger(SettingsPage.class.getName());

    private final By profilePictureUrl = By.xpath("//input[@placeholder='URL of profile picture']");
    private final By shortBioTextarea = By.xpath("//textarea[@placeholder='Short bio about you']");
    private final By updateSettingsButton = By.xpath("//button[contains(text(),'Update Settings')]");

    public void setProfilePictureUrl(String url) {
        waitUntilVisibleByLocator(profilePictureUrl);
        clearTextFromElement(profilePictureUrl);
        sendTextToElement(profilePictureUrl,url);
        log.info("Profile Picture URL: ".concat(url));
        Listener.test.get().info("Profile Picture URL: ".concat(url));
    }

    public void setShortBio(String shortBio) {
        waitUntilVisibleByLocator(shortBioTextarea);
        clearTextFromElement(shortBioTextarea);
        sendTextToElement(shortBioTextarea,shortBio);
        log.info("Short Bio: ".concat(shortBio));
        Listener.test.get().info("Short Bio: ".concat(shortBio));
    }

    public void clickUpdateSettings() {
        clickWebElement(updateSettingsButton);
        log.info("Update Settings button is clicked...");
        Listener.test.get().info("Update Settings button is clicked...");
    }
}
