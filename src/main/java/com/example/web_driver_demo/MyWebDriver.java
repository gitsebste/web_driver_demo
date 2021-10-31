package com.example.web_driver_demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
        list.get(0).click();
    }

    private List<WebElement> getElementsBySelector(String selector) {
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
