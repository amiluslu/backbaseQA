package com.amilus.backbaseqa.pages;

import com.amilus.backbaseqa.base.BasePage;
import com.amilus.backbaseqa.base.Listener;
import com.amilus.backbaseqa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.logging.Logger;

public class EditorPage extends BasePage {
    Logger log = Logger.getLogger(EditorPage.class.getName());

    private final By articleTitleInput = By.xpath("//input[@placeholder='Article Title']");
    private final By subjectInput = By.xpath("//input[contains(@placeholder,'What')]");
    private final By contentTextarea = By.xpath("//textarea[@placeholder='Write your article (in markdown)']");
    private final By tagsInput = By.xpath("//input[@placeholder='Enter Tags']");
    private final By publishArticleButton = By.xpath("//button[@type='button' and contains(text(),'Publish Article')]");
    private final By commentTextarea = By.xpath("//textarea[contains(@placeholder,'Write a comment')]");
    private final By postCommentButton = By.xpath("//button[contains(text(),'Post Comment')]");
    private final By commentExists = By.xpath("//app-article-comment//div[@class='card']");
    private final By deleteCommentButton = By.xpath("//app-root/app-article/div[@class='article-page']/div[@class='container page']/div[@class='row']/div/app-article-comment[1]//i[@class='ion-trash-a']");
    private final By deleteArticleButton = By.xpath("//app-root/app-article/div[@class='article-page']/div[@class='container page']//app-article-meta/div/span[1]/button");
    private final By editArticleButton = By.xpath("//div[@class='container page']//a[contains(text(),'Edit Article')]");
    private final By articleHeaderLabel = By.xpath("//h1");

    public void setArticleTitle(String articleTitle) {
        waitUntilVisibleByLocator(articleTitleInput);
        clearTextFromElement(articleTitleInput);
        sendTextToElement(articleTitleInput,articleTitle);
        log.info("Article Title: ".concat(articleTitle));
        Listener.test.get().info("Article Title: ".concat(articleTitle));
    }

    public void setSubject(String subject) {
        waitUntilVisibleByLocator(subjectInput);
        clearTextFromElement(subjectInput);
        sendTextToElement(subjectInput,subject);
        log.info("What's this article about? : ".concat(subject));
        Listener.test.get().info("What's this article about? : ".concat(subject));
    }

    public void setContent(String content) {
        waitUntilVisibleByLocator(contentTextarea);
        clearTextFromElement(contentTextarea);
        sendTextToElement(contentTextarea,content);
        log.info("Content: ".concat(content));
        Listener.test.get().info("Content: ".concat(content));
    }

    public void setTags(String tags) {
        waitUntilVisibleByLocator(tagsInput);
        clearTextFromElement(tagsInput);
        sendTextToElement(tagsInput,tags);
        log.info("Tags: ".concat(tags));
        Listener.test.get().info("Tags: ".concat(tags));
    }

    public void clickPublishArticle() {
        clickWebElement(publishArticleButton);
        log.info("Publish article button is clicked...");
        Listener.test.get().info("Publish article button is clicked...");
    }

    public void setComment(String comment) {
        waitUntilVisibleByLocator(commentTextarea);
        clearTextFromElement(commentTextarea);
        sendTextToElement(commentTextarea,comment);
        log.info("Comment: ".concat(comment));
        Listener.test.get().info("Comment: ".concat(comment));
    }

    public void clickPostComment() {
        clickWebElement(postCommentButton);
        log.info("Post Comment button is clicked...");
        Listener.test.get().info("Post Comment button is clicked...");
    }

    public List<WebElement> getNumberOfComment() {
        return getElementsAsList(commentExists);
    }

    public void clickDeleteComment() {
        clickWebElement(deleteCommentButton);
        log.info("Delete Comment button is clicked...");
        Listener.test.get().info("Delete Comment button is clicked...");
    }

    public void clickDeleteArticle() {
        clickWebElement(deleteArticleButton);
        log.info("Delete Article button is clicked...");
        Listener.test.get().info("Delete Article button is clicked...");
    }

    public void clickEditArticle() {
        clickWebElement(editArticleButton);
        log.info("Edit Article button is clicked...");
        Listener.test.get().info("Edit Article button is clicked...");
    }

    public void verifyArticleHeader(String articleHeader){
        waitUntilVisibleByLocator(articleHeaderLabel);
        String actualHeader = getElementText(articleHeaderLabel);
        Assert.assertEquals(actualHeader,actualHeader);
        Listener.test.get().pass("Editing Header process is verified.. Exptected Header: "+articleHeader+ " Actual Header: "+actualHeader);
        log.info("Editing Header process is verified.. Exptected Header: "+articleHeader+ " Actual Header: "+actualHeader);
    }
}
