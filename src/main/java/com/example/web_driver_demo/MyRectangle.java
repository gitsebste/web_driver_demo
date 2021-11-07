package com.example.web_driver_demo;

import org.openqa.selenium.Rectangle;

public class MyRectangle {
    public final int x_min;
    public final int width;
    public final int y_min;
    public final int height;
    public final int x_average;
    public final int y_average;

    public MyRectangle(Rectangle seleniumRect) {
        int x_diff_for_the_resolution = 0;
        int y_diff_for_the_resolution = 50;
        x_min = seleniumRect.getX()+x_diff_for_the_resolution;
        y_min = seleniumRect.getY()+y_diff_for_the_resolution;
        width = seleniumRect.getWidth();
        height = seleniumRect.getHeight();
        x_average = (x_min+(x_min+width))/2;
        y_average = (y_min+(y_min+height))/2;
    }
}
