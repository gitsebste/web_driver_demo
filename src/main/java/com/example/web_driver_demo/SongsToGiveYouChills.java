package com.example.web_driver_demo;

import utils.Waiting;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SongsToGiveYouChills implements GetPageable{

    private final MyWebDriver myDriver;
    private static final int MAX_BOOK_ID = 19446;
    private List<GutenProject.Book> tenLongestBooks;
    private static Pattern numPattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    SongsToGiveYouChills(){
        // https://open.spotify.com/playlist/0gUDCB11A5jL1CyLM9h1fK
        myDriver = new MyWebDriver();
    }

    public void getPage() {
        String url = "https://open.spotify.com/playlist/0gUDCB11A5jL1CyLM9h1fK";
        myDriver.getPage(url);
        closeCookiePopup();
    }

    private void closeCookiePopup() {
        myDriver.clickElementBySelector("#onetrust-accept-btn-handler");
    }

    public boolean isLastSongReachable() {
        maximizeAndEnableScrolling();
        MyRobot.goToBottomOfThePage();
        String _715_selector = "#main > div > div.Root__top-container > div.Root__main-view > main > div.os-host.os-host-foreign.os-theme-spotify.os-host-resize-disabled.os-host-scrollbar-horizontal-hidden.main-view-container__scroll-node.os-host-transition.os-host-overflow.os-host-overflow-y > div.os-padding > div > div > div.main-view-container__scroll-node-child > section > div._5wXWalxnOyFQX7uHu_j > div:nth-child(3) > div > div.iDlSBR5JgCntHwvGPaQk > div:nth-child(2) > div:nth-child(31) > div > div.F_uA1XhGWiyt9O0zrDiB > div > span";
        String txt = myDriver.getTextBySelector(_715_selector);

        return txt.equals("715");
    }

    public Set<Object> getAllSongs() {
        maximizeAndEnableScrolling();
        Set<String> allSongs = new HashSet<>();
        for (int i = 1; i <= 14; i++) {
            int upperBound = i * 50;
            allSongs.addAll(getVisibleSongs(upperBound-49,upperBound));
            for (int j = 0; j < 5; j++) scrollDownAndWait(1_000);
        }
        allSongs.addAll(getVisibleSongs(701,715));
        return Set.of(allSongs.toArray());
    }

    private void scrollDown() {
        MyRobot.scrollDown();
    }

    private void scrollDownAndWait(int millis) {
        MyRobot.scrollDown();
        waitMillis(millis);
    }

    private void waitMillis(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Set<String> getVisibleSongs(int lowerBound, int upperBound) {
        String selector = "#main > div > div.Root__top-container > div.Root__main-view > main > div.os-host.os-host-foreign.os-theme-spotify.os-host-resize-disabled.os-host-scrollbar-horizontal-hidden.main-view-container__scroll-node.os-host-transition.os-host-overflow.os-host-overflow-y > div.os-padding > div > div > div.main-view-container__scroll-node-child > section > div._5wXWalxnOyFQX7uHu_j > div:nth-child(3) > div > div.iDlSBR5JgCntHwvGPaQk > div:nth-child(2)";
        //List<String> visibleSongs = myDriver.setListOfAllGrandChildrenFromParentSelector(selector);
        Waiting.runUntilSuccessOrTimeout(()->{
            //1
            //Only Love Can Break Your Heart
            //Neil Young
            //After the Gold Rush (2009 Remaster)
            //18 days ago
            //3:08
            //myDriver.driver.getPageSource();
            boolean val = myDriver.
                    setListOfAllGrandChildrenFromParentSelector(selector,false)
                    .stream()
                    .map(s->s.split("\n")[0])
                    .filter(s-> numPattern.matcher(s).matches())
                    .map(s -> Integer.valueOf(s))
                    .filter(v -> v >= lowerBound && v <= upperBound)
                    .count() == upperBound - lowerBound + 1;
        });

        List<String> visibleSongs = myDriver.getListOfAllGrandChildrenFromParentSelector();
        //System.out.println(visibleSongs);
        // throw new IllegalArgumentException("");
        return visibleSongs.stream().collect(Collectors.toSet());
    }

    private void maximizeAndEnableScrolling() {
        String titleColumnNameSelector = "#main > div > div.Root__top-container > div.Root__main-view > main > div.os-host.os-host-foreign.os-theme-spotify.os-host-resize-disabled.os-host-scrollbar-horizontal-hidden.main-view-container__scroll-node.os-host-transition.os-host-overflow.os-host-overflow-y > div.os-padding > div > div > div.main-view-container__scroll-node-child > section > div._5wXWalxnOyFQX7uHu_j > div:nth-child(3) > div > div.uwzZYE9AYS0OMBzvAopr > div > div.MqCiGAC89rMLoCrDazR6 > div > span";
        MyRobot.maximize();
        MyRectangle rect = new MyRectangle(
                myDriver.getRectangleBySelector(titleColumnNameSelector));
        MyRobot.clickElementByRectangle(
                rect
        );
        MyRobot.mouseMove(rect.x_min-50,rect.y_min);
    }
}
