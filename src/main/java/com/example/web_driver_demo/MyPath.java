package com.example.web_driver_demo;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class MyPath {

    private static String getFilePathByFileName(String fileName){
        try {
            File file = ResourceUtils.getFile(fileName);
            return file.getAbsolutePath();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("getFilePathByFileName: "+fileName);
        }
    }

    public static String getChromeDriverPath() {
        return getFilePathByFileName("chromedriver");
    }
    public static String getSongsFile() {
        return getFilePathByFileName("songs.txt");
    }
}
