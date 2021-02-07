package com.amilus.backbaseqa.cases;

import com.amilus.backbaseqa.base.TestBase;
import com.amilus.backbaseqa.pages.HomePage;
import com.amilus.backbaseqa.pages.SettingsPage;
import com.amilus.backbaseqa.pages.SignUpPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class SignUpCase extends TestBase {

    private SignUpPage signUpPage = new SignUpPage();
    private HomePage homePage = new HomePage();
    private SettingsPage settingsPage = new SettingsPage();

    @Test(priority = 0)
    public void signUpWithNonValidEmail() throws InterruptedException {
        homePage.clickSignUpLink();
        String username = signUpPage.randomStringGenerator();
        signUpPage.setUsername(username);
        String email = signUpPage.randomStringGenerator();
        signUpPage.setEmail(email);
        String password = signUpPage.randomStringGenerator();
        signUpPage.setPassword(password);
        TimeUnit.SECONDS.sleep(1);
        signUpPage.clickSignUpSubmit();
        TimeUnit.SECONDS.sleep(1);
        if(signUpPage.validateErrorMessage() != null){
            for (WebElement webElement : signUpPage.validateErrorMessage()) {
                if (webElement.getText().contains("email is invalid")){
                    Assert.assertTrue(true,"Email is not valid. Please provide valid email..");
                    break;
                }
                else {
                    Assert.fail("Unexpected error occured !! Error detail: "+webElement.getText());
                }
            }
        }
    }

    @Test(priority = 1)
    public void signUpWithValidData() throws InterruptedException {
        homePage.clickSignUpLink();
        String username = signUpPage.randomStringGenerator();
        signUpPage.setUsername(username);
        String email = signUpPage.randomStringGenerator()+"@random.com";
        signUpPage.setEmail(email);
        signUpPage.setPassword("amilus321");
        TimeUnit.SECONDS.sleep(1);
        signUpPage.clickSignUpSubmit();
        TimeUnit.SECONDS.sleep(1);
        String actualUsernameLabel = signUpPage.validateLogin(username.toLowerCase());
        Assert.assertEquals(actualUsernameLabel,username.toLowerCase());
    }

    @Test(priority = 2)
    public void updateSettings(){
        homePage.clickSettings();
        settingsPage.waitUntilUrlContains("/settings");
        settingsPage.setProfilePictureUrl("http://profilepicture");
        settingsPage.setShortBio("short bio of amilus");
        settingsPage.clickUpdateSettings();
    }
}
