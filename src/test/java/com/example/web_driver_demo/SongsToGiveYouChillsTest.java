package com.example.web_driver_demo;

import com.example.web_driver_demo.songs.Song;
import com.example.web_driver_demo.songs.SongsToGiveYouChills;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SongsToGiveYouChillsTest {

    @Test void testSortedSongs(){
        List<Song> songs = SongsToGiveYouChills.getSortedSongsById();
        Assertions.assertEquals(715,songs.size());
    }
}