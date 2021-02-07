package com.amilus.backbaseqa.base;

import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class BasePage {

    Logger log = Logger.getLogger(BasePage.class.getName());

    public WebElement waitUntilVisibleByLocator(By locator)
    {
        WebElement webElement = null;
        try
        {
            TimeUnit.SECONDS.sleep(1);
            Wait<WebDriver> wait = (new FluentWait(TestBase.driver)).withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofSeconds(1)).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
        catch (Throwable t)
        {
            Assert.fail(t.getMessage());
            Listener.test.get().fail("waitUntilVisibleByLocator failed: "+t.getMessage());
            log.severe("waitUntilVisibleByLocator failed: "+t.getMessage());
        }
        return webElement;
    }


    public WebElement waitUntilClickable(By locator)
    {
        WebElement webElement = null;
        try
        {
            TimeUnit.SECONDS.sleep(1);
            Wait<WebDriver> wait = (new FluentWait(TestBase.driver)).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(1)).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            webElement = wait.until(ExpectedConditions.elementToBeClickable(locator));
        }
        catch (Throwable t)
        {
            Assert.fail(t.getMessage());
            Listener.test.get().fail("waitUntilClickable failed: "+t.getMessage());
            log.severe("waitUntilClickable failed: "+t.getMessage());
        }
        return webElement;
    }

    public void sendTextToElement(By locator,String text)
    {
        WebElement webElement = null;
        try
        {
            webElement = TestBase.driver.findElement(locator);
            webElement.sendKeys(text);
        }
        catch (Throwable t)
        {
            Assert.fail(t.getMessage());
            Listener.test.get().fail("sendTextToElement failed: "+t.getMessage());
            log.severe("sendTextToElement failed: "+t.getMessage());
        }
    }

    public void clearTextFromElement(By locator)
    {
        WebElement webElement = null;
        try
        {
            webElement = TestBase.driver.findElement(locator);
            webElement.clear();
        }
        catch (Throwable t)
        {
            Assert.fail(t.getMessage());
            Listener.test.get().fail("clearTextFromElement failed: "+t.getMessage());
            log.severe("clearTextFromElement failed: "+t.getMessage());
        }
    }

    public void clickWebElement(By locator)
    {
        WebElement webElement = null;
        try
        {
            webElement = TestBase.driver.findElement(locator);
            waitUntilClickable(locator);
            webElement.click();
        }
        catch (Throwable t)
        {
            Assert.fail(t.getMessage());
            Listener.test.get().fail("clickWebElement failed: "+t.getMessage());
            log.severe("clickWebElement failed: "+t.getMessage());
        }
    }

    public List<WebElement> getElementsAsList(By locator)
    {
        List<WebElement> webElementList = null;
        try
        {
            TimeUnit.SECONDS.sleep(1);
            webElementList = TestBase.driver.findElements(locator);
        }
        catch (Throwable t)
        {
            Assert.fail(t.getMessage());
            Listener.test.get().fail("getElementsAsList failed: "+t.getMessage());
            log.severe("getElementsAsList failed: "+t.getMessage());
        }
        return webElementList;
    }

    public String getElementText(By locator)
    {
        WebElement webElement = null;
        try
        {
            webElement = TestBase.driver.findElement(locator);
            return webElement.getText();
        }
        catch (Throwable t)
        {
            Assert.fail(t.getMessage());
            Listener.test.get().fail("getElementText failed: "+t.getMessage());
            log.severe("getElementText failed: "+t.getMessage());
            return null;
        }
    }

    public void waitUntilUrlContains(String url)
    {
        try
        {
            TimeUnit.SECONDS.sleep(1);
            Wait<WebDriver> wait = (new FluentWait(TestBase.driver)).withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofSeconds(1)).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            boolean result = wait.until(ExpectedConditions.urlContains(url));
            if(result){
                Assert.assertTrue(result,"URL verification passed..");
                Listener.test.get().pass("URL verification passed: "+TestBase.driver.getCurrentUrl());
            }
            else {
                Assert.fail("URL verification failed !!");
                Listener.test.get().fail("URL verification failed: Expected URL: "+url+" Actual URL: "+TestBase.driver.getCurrentUrl());
            }
        }
        catch (Throwable t)
        {
            Assert.fail(t.getMessage());
            Listener.test.get().fail("waitUntilUrlContains failed: "+t.getMessage());
            log.severe("waitUntilUrlContains failed: "+t.getMessage());
        }
    }
}
