package com.amilus.backbaseqa.pages;

import com.amilus.backbaseqa.base.BasePage;
import com.amilus.backbaseqa.base.Listener;
import org.openqa.selenium.By;

import java.util.logging.Logger;

public class HomePage extends BasePage {

    Logger log = Logger.getLogger(HomePage.class.getName());

    private final By settingsLabel = By.xpath("//a[@routerlink='/settings']");
    private final By signUpLinkButton = By.xpath("//a[@routerlink='/register']");
    private final By signInLinkButton = By.xpath("//a[@routerlink='/login']");

    public void clickSettings() {
        clickWebElement(settingsLabel);
        log.info("Settings button is clicked...");
        Listener.test.get().info("Settings button is clicked...");
    }

    public void clickSignUpLink() {
        clickWebElement(signUpLinkButton);
        log.info("Sign Up link button is clicked...");
        Listener.test.get().info("Sign Up link button is clicked...");
    }

    public void clickSignInLink() {
        clickWebElement(signInLinkButton);
        log.info("Sign In link button is clicked...");
        Listener.test.get().info("Sign In link button is clicked...");
    }
}
