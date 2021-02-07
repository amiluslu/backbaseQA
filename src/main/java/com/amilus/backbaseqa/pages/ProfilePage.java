package com.amilus.backbaseqa.pages;

import com.amilus.backbaseqa.base.BasePage;
import com.amilus.backbaseqa.base.Listener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.logging.Logger;

public class ProfilePage extends BasePage {

    Logger log = Logger.getLogger(ProfilePage.class.getName());

    private final By articleListItemXpath = By.xpath("//app-article-list-item");
    private final By newpostLinkButton = By.xpath("//a[@routerlink='/editor']");
    private final By firstArticleXpath = By.xpath("//app-root/app-profile/div[@class='profile-page']//app-profile-articles/app-article-list/app-article-list-item[1]//h1");

    public void clickUsernameLabel(String username) {
        By usernameLabelXpath = By.xpath("//app-root/app-navbar/nav[@class='navbar navbar-light']//ul[@class='nav navbar-nav pull-xs-right']//a[@href='#/profile/"+username+"']");
        clickWebElement(usernameLabelXpath);
        log.info("Username label is clicked...");
        Listener.test.get().info("Username label is clicked...");
    }

    public int getArticleListItemCount() {
        List<WebElement> articleListItem = getElementsAsList(articleListItemXpath);
        Listener.test.get().info("Article List Count: "+articleListItem.size());
        return articleListItem.size();
    }

    public void clickNewPost() {
        clickWebElement(newpostLinkButton);
        log.info("Sign In link button is clicked...");
        Listener.test.get().info("Sign In link button is clicked...");
    }

    public void clickFirstArticle() {
        clickWebElement(firstArticleXpath);
        log.info("Clicked First Article...");
        Listener.test.get().info("Clicked First Article...");
    }
}
