package com.example.web_driver_demo;

import utils.Waiting;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class AuchanProm {
    private static final int PRODUCTS_PER_PAGE = 15;
    MyWebDriver myWebDriver = new MyWebDriver();
    private int numberOfAllProducts;
    private int numberOfReachableProducts;
    private String numberOfProductsTxt=""; // Załadowano 15 produkt(y) na 213

    public void getPromPage() {
        myWebDriver.getPage("https://zakupy.auchan.pl/shop/boutique.b-172/promocje.c-19138?qq=%7B%7D");
        closePopups();
        getNumberOfProductsText();
    }

    private void closePopups() {
        String rejectCookiesSelector = "#onetrust-reject-all-handler";
        String closeAddSelector = "#__layout > div > div.vue-portal-target > div > div > div > button";

        myWebDriver.clickElementsBySelector(
                rejectCookiesSelector,
                closeAddSelector
        );
    }

    private void getNumberOfProductsText() {
        String selector = "#container > div._2UFX > div > div > div > div._3je0 > div > div._2UAB._1stj._2Xx8._2_kO._2Vfs > div > div > div._2UAB._1stj._2Xx8._2Pjf._3Zsz > div > div._2xr_";
        numberOfProductsTxt = myWebDriver.getTextBySelector(selector);
    }
    public boolean textFound(){
        return numberOfProductsTxt.length()>0;
    }

    private void setNumbersOfProducts() {
        //0          1  2          3  4     // word indexes
        //Załadowano 15 produkt(y) na 213   // similar words in numberOfProductsTxt
        String[] words = numberOfProductsTxt.split(" ");
        String numOfReachableProductsStr = words[1];
        String numOfAllProductsStr = words[4];
        numberOfReachableProducts = Integer.valueOf(numOfReachableProductsStr);
        numberOfAllProducts = Integer.valueOf(numOfAllProductsStr);
    }

    public void tryToReachAllProducts() {
        getNumberOfProductsText();
        setNumbersOfProducts();
        final double numPerPage = PRODUCTS_PER_PAGE;
        final double all = numberOfAllProducts;
        final double reachable = numberOfReachableProducts;
        int pagesToLoad = (int) Math.ceil((all-reachable)/numPerPage);
        loadPages(pagesToLoad);
    }

    private void loadPages(int pagesToLoad) {
        Stream.iterate(0, n -> n + 1)
                .limit(pagesToLoad)
                .forEach(n->loadPage());
    }

    private void loadPage() {
        String selector = "#container > div._2UFX > div > div > div > div._3je0 > div > div._2UAB._1stj._2Xx8._2_kO._2Vfs > div > div > div._2UAB._1stj._2Xx8._2Pjf._3Zsz > div > div._3wx_ > button";
        myWebDriver.clickElementBySelector(selector);
    }

    private int getNumberOfReachableProducts(){
        return numberOfReachableProducts;
    }

    public boolean AreAllProductsReachable() {
        getNumberOfProductsText();
        setNumbersOfProducts();

        Waiting.runUntilStableStateOrTimeout(
                ()-> {
                    getNumberOfProductsText();
                    setNumbersOfProducts();
                    return getNumberOfReachableProducts();
                },
                numberOfAllProducts
        );
        return numberOfReachableProducts == numberOfAllProducts;
    }
}
