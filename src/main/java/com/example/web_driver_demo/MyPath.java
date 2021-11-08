package com.example.web_driver_demo;

import java.io.File;

public class MyPath {
    public static String getChromeDriverPath() {
        File resourcesDirectory = new File("chromedriver");
        String path = resourcesDirectory.getAbsolutePath();
        return path;
    }
    public static String getSongsFile() {
        File resourcesDirectory = new File("songs.txt");
        String path = resourcesDirectory.getAbsolutePath();
        return path;
    }
}
