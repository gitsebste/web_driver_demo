package com.example.web_driver_demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Waiting;

import java.util.Arrays;
import java.util.List;

public class MyWebDriver {
    final WebDriver driver;

    MyWebDriver() {
        setUpSystem();
        driver = new ChromeDriver();
    }

    public void getPage(String url) {
        driver.get(url);
        throwOrGetListBySelector("html");
    }

    public void clickElementBySelector(String selector) {
        var list = throwOrGetListBySelector(selector);
        Runnable click = ()-> list.get(0).click();
        clickUntilSuccessOneSecTimeout(click);
    }

    private void clickUntilSuccessOneSecTimeout(Runnable run) {
        Waiting.runUntilSuccessOrTimeout(run);
    }

    public void clickElementsBySelector(String... selectors) {
        Arrays.stream(selectors).forEach(
                selector ->clickElementBySelector(selector)
        );
    }

    private List<WebElement> getElementsBySelector(String selector) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));

        var list = driver.findElements(By.cssSelector(selector));
        return list;
    }

    public void typeIntoElementBySelector(String selector,
                                          CharSequence sequence) {
        var list = throwOrGetListBySelector(selector);
        list.get(0).sendKeys(sequence);
    }

    private List<WebElement> throwOrGetListBySelector(String selector) {
        var list = getElementsBySelector(selector);
        int size = list.size();
        if (size == 0)
            throw new IllegalArgumentException(
                    "throwOrGetListBySelector: No elements for selector "
                            + selector);
        return list;
    }

    private void setUpSystem() {
        String key = "webdriver.chrome.driver";
        String path = MyPath.getChromeDriverPath();
        System.setProperty(key, path);
    }

    public String getTextBySelector(String selector) {
        var list = throwOrGetListBySelector(selector);
        return list.get(0).getText();
    }
}
