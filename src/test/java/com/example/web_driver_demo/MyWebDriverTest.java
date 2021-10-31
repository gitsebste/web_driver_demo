package com.example.web_driver_demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MyWebDriverTest {

    @Test
    void getPage(){
        MyWebDriver myWebDriver = new MyWebDriver();
        String url = "https://duckduckgo.com/"; // "https://en.wikipedia.org/wiki/Selenium_(software)";
        myWebDriver.getPage(url);

        String selector = "#search_button_homepage";
        String exceptionSelector = "#e";
        String inputSearchSelector = "#search_form_input_homepage";
        Assertions.assertAll(
                () -> Assertions.assertEquals(
                        1, myWebDriver.getElementsBySelector(selector).size()
                ),
                () -> Assertions.assertThrows(
                        IllegalArgumentException.class,
                        () ->myWebDriver.clickElementBySelector(exceptionSelector)
                ),
                () -> Assertions.assertThrows(
                        IllegalArgumentException.class,
                        () ->myWebDriver.typeIntoElementBySelector(exceptionSelector,"")
                ),
                () -> Assertions.assertDoesNotThrow(
                        () ->myWebDriver.typeIntoElementBySelector(inputSearchSelector,"driver" )
                ),
                () -> Assertions.assertDoesNotThrow(
                        () ->myWebDriver.clickElementBySelector(selector)
                )
        );
    }
}
