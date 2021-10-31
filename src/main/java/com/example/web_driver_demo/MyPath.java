package com.example.web_driver_demo;

import java.io.File;

public class MyPath {
    public static String getChromeDriverPath() {
        File resourcesDirectory = new File("chromedriver");
        String path = resourcesDirectory.getAbsolutePath();
        return path;
    }
}
