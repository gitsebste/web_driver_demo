package com.example.web_driver_demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.List;

public class MyWebDriver {

    final WebDriver driver;

    MyWebDriver() {
        setUpSystem();
        driver = new ChromeDriver();
    }

    public void getPage(String url) {
        driver.get(url);
    }

    public void clickElementBySelector(String selector) {
        var list = throwOrGetListBySelector(selector);
        list.get(0).click();
    }

    public List<WebElement> getElementsBySelector(String selector) {
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
        File resourcesDirectory = new File("chromedriver");
        String path = resourcesDirectory.getAbsolutePath();
        System.setProperty(key, path);
    }

}
