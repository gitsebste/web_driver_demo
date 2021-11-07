package com.example.web_driver_demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class SongsToGiveYouChillsTest {

    private static SongsToGiveYouChills songsToGiveYouChills;

    @BeforeAll
    static void init() {

        songsToGiveYouChills = new SongsToGiveYouChills();
        songsToGiveYouChills.getPage();
    }

    @Test
     void testGetPage() {

        boolean actual = songsToGiveYouChills.isLastSongReachable();

        Assertions.assertTrue(actual);
    }

    @Test
    void testGetAllSongs() {

        Set<Object> songs = songsToGiveYouChills.getAllSongs();
        songs.stream().forEach(System.out::println);
        Assertions.assertEquals(715,songs.size());
    }
}