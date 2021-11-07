package com.example.web_driver_demo;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MyRobot {
    private static boolean maximized=false;
    private static Robot robot;

    static {
        System.setProperty("java.awt.headless", "false");
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void clickElementByRectangle(MyRectangle rectangle) {
        maximizeIfNotAlready();
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mouseMove(rectangle.x_average, rectangle.y_average);

        mouseLeftClick();

        System.out.println("Mouse in place");
    }

    private static void mouseLeftClick() {
        int mask = InputEvent.BUTTON1_DOWN_MASK;
        mouseClick(mask);
    }

    private static void mouseClick(int mask) {
        robot.mousePress(mask);
        robot.mouseRelease(mask);
    }

    public static void maximize(){
        maximizeIfNotAlready();
    }

    private static void maximizeIfNotAlready() {
        if(! maximized) {
            robot.keyPress(KeyEvent.VK_F11);
            robot.keyRelease(KeyEvent.VK_F11);
            maximized=true;
        }
    }

    public static void goToBottomOfThePage() {
        int keyCode = KeyEvent.VK_END;
        clickKey(keyCode);
    }

    private static void clickKey(int keyCode) {
        robot.keyPress(keyCode);
        robot.keyRelease(keyCode);
    }

    public static void scrollDown() {
        int keyCode = KeyEvent.VK_PAGE_DOWN;
        clickKey(keyCode);
        // System.out.println( "scrolled down" );
    }

    public static void mouseMove(int x, int y) {
        robot.mouseMove(x,y);
    }
}
