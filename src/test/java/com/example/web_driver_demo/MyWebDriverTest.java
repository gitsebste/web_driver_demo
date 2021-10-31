package com.example.web_driver_demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyWebDriverTest {

    private static MyWebDriver myWebDriver;
    private static final String regularUrl = "https://duckduckgo.com/";
    private static final String exceptionSelector="#e";

    @BeforeAll
    static void init() {
        myWebDriver = new MyWebDriver();
    }

    @Test
    void testGetPage() {
        Assertions.assertAll(
                emptyUrlThrowsGetPage(),
                regularUrlDoesNotThrowGetPage()
        );
    }

    private Executable emptyUrlThrowsGetPage() {
        return () -> Assertions.assertThrows(
                org.openqa.selenium.InvalidArgumentException.class,
                () -> myWebDriver.getPage("")
        );
    }

    private Executable regularUrlDoesNotThrowGetPage() {
        return () -> Assertions.assertDoesNotThrow(
                () -> myWebDriver.getPage(regularUrl)
        );
    }

    @Test
    void testClickElementBySelector() {
        myWebDriver.getPage(regularUrl);
        Assertions.assertAll(
                exceptionSelectorThrowsClickElementBySelector(),
                regularSelectorDoesNotThrowClickElementBySelector()
        );
    }

    private Executable exceptionSelectorThrowsClickElementBySelector() {
        return () -> Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> myWebDriver.clickElementBySelector(exceptionSelector)
        );
    }

    private Executable regularSelectorDoesNotThrowClickElementBySelector() {
        String regularSelector = "#search_button_homepage";
        return () -> Assertions.assertDoesNotThrow(
                () -> myWebDriver.clickElementBySelector(regularSelector)
        );
    }

    @Test
    void testTypeIntoElementBySelector() {
        myWebDriver.getPage(regularUrl);
        Assertions.assertAll(
                exceptionSelectorThrowsTypeInto(),
                inputSearchSelectorDoesNotThrowTypeInto()
        );
    }

    private Executable exceptionSelectorThrowsTypeInto() {
        return () -> Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> myWebDriver.typeIntoElementBySelector(exceptionSelector, "")
        );
    }

    private Executable inputSearchSelectorDoesNotThrowTypeInto() {
        String inputSearchSelector = "#search_form_input_homepage";
        return () -> Assertions.assertDoesNotThrow(
                () -> myWebDriver.typeIntoElementBySelector(inputSearchSelector, "driver")
        );
    }
}
