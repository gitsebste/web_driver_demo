package com.example.web_driver_demo.songs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/")
public class SongController {
    @GetMapping(path = "songs")
    public List<Song> getAllSongs(){
        return SongsToGiveYouChills.getSortedSongsById();
    }
}
