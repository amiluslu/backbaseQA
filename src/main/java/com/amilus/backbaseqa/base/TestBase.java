package com.amilus.backbaseqa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    private int default_pageLoadWait = 120;
    public static WebDriver driver;
    private static String OS = System.getProperty("os.name").toLowerCase();

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
            boolean headlessToggle = Boolean.parseBoolean(props.getProperty("headless"));
            if (OS.contains("win")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver.exe");
            }
            else {
                String macPath = System.getProperty("user.dir") + "/driver/chromedriver";
                System.setProperty("webdriver.chrome.driver", macPath);
                chmodChange(macPath);
            }
            ChromeOptions chromeOptions = new ChromeOptions();
            if(headlessToggle){
                chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
            }
            else {
                driver = new ChromeDriver();
            }

        }
        catch (Throwable t ){
            t.printStackTrace();
        }
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        driver.navigate().to("https://candidatex:qa-is-cool@qa-task.backbasecloud.com/#/");
        driver.manage().timeouts().pageLoadTimeout(default_pageLoadWait, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {

    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        if (driver != null && !driver.toString().contains("null")) {
            driver.quit();
        }
    }

    private static void chmodChange(String filePath) {
        File file = new File(filePath);
        file.setExecutable(true);
        file.setReadable(true);
        file.setWritable(true);
    }
}
