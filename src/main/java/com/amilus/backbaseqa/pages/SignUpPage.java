package com.amilus.backbaseqa.pages;

import com.amilus.backbaseqa.base.BasePage;
import com.amilus.backbaseqa.base.Listener;
import com.amilus.backbaseqa.reporting.ReportFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.logging.Logger;

public class SignUpPage extends BasePage {

    Logger log = Logger.getLogger(SignUpPage.class.getName());

    private final By usernameInput = By.xpath("//input[@placeholder='Username']");
    private final By emailInput = By.xpath("//input[@placeholder='Email']");
    private final By passwordInput = By.xpath("//input[@placeholder='Password']");
    private final By signUpSubmitButton = By.xpath("//app-root/app-register/div[@class='auth-page']/div[@class='container page']//button[@class='btn btn-lg btn-primary pull-xs-right']");
    private final By signInSubmitButton = By.xpath(" //app-root/app-login/div[@class='auth-page']/div[@class='container page']//button[@class='btn btn-lg btn-primary pull-xs-right']");
    private final By errorMessagesXpath = By.xpath("//app-list-errors/ul[@class='error-messages']/li");

    public String randomStringGenerator() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(5);
        for (int i = 0; i < 5; i++) {
            int index
                    = (int)(alphabet.length()
                    * Math.random());
            sb.append(alphabet
                    .charAt(index));
        }
        return sb.toString();
    }

    public void setUsername(String username) {
        waitUntilVisibleByLocator(usernameInput);
        clearTextFromElement(usernameInput);
        sendTextToElement(usernameInput,username);
        log.info("Username: ".concat(username));
        Listener.test.get().info("Username: ".concat(username));
    }

    public void setPassword(String password) {
        waitUntilVisibleByLocator(passwordInput);
        clearTextFromElement(passwordInput);
        sendTextToElement(passwordInput,password);
        log.info("Password: *********");
        Listener.test.get().info("Pasword: *********");
    }

    public void setEmail(String email) {
        waitUntilVisibleByLocator(emailInput);
        clearTextFromElement(emailInput);
        sendTextToElement(emailInput,email);
        log.info("Email: ".concat(email));
        Listener.test.get().info("Email: ".concat(email));
    }

    public void clickSignUpSubmit() {
        clickWebElement(signUpSubmitButton);
        log.info("Sign Up submit button is clicked...");
        Listener.test.get().info("Sign Up submit button is clicked...");
    }

    public void clickSignInSubmit() {
        clickWebElement(signInSubmitButton);
        log.info("Sign In submit button is clicked...");
        Listener.test.get().info("Sign In submit button is clicked...");
    }

    public List<WebElement> validateErrorMessage(){
        List<WebElement> getErrorMessages = getElementsAsList(errorMessagesXpath);
        if( getErrorMessages != null && getErrorMessages.size()> 0){
            return getErrorMessages;
        }
        else {
            return null;
        }
    }

    public String validateLogin(String username) {
        By usernameLabelXpath = By.xpath("//app-root/app-navbar/nav[@class='navbar navbar-light']//ul[@class='nav navbar-nav pull-xs-right']//a[@href='#/profile/"+username+"']");
        waitUntilVisibleByLocator(usernameLabelXpath);
        String actualUsername = getElementText(usernameLabelXpath);
        return actualUsername;
    }
}
