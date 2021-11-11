package com.example.web_driver_demo;

import com.example.web_driver_demo.songs.Song;
import com.example.web_driver_demo.songs.SongsToGiveYouChills;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SongsToGiveYouChillsTest {

    private static SongsToGiveYouChills songsToGiveYouChills;

//    @BeforeAll static void init() {
//
//        songsToGiveYouChills = new SongsToGiveYouChills();
//        songsToGiveYouChills.getPage();
//    }

    @Test void testGetPage() {

        boolean actual = songsToGiveYouChills.isLastSongReachable();

        Assertions.assertTrue(actual);
    }

    @Test void testSortedSongs(){
        List<Song> songs = songsToGiveYouChills.getSortedSongsById();
        Assertions.assertEquals(715,songs.size());
        songs.stream().forEach(System.out::println);
    }
}