package com.example.web_driver_demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Song {
    private static String delimiter;
    public final int id;
    public final String title;
    public final String artist;
    public final String album;
    public final int durationSecs;

    public Song(int id, String title, String artist, String album,
                int durationSecs) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.durationSecs = durationSecs;
    }
    public static Song ofStringWithDelimiter(String songAsString){
        try {
            return unsafeConvert(repairForKnownGotchas(songAsString));
        }catch (Exception e){
            String msg = "Not posssible to convert:\n" + songAsString + "\nto a song";
            System.out.println(msg);
            throw e;
        }
    }

    private static String repairForKnownGotchas(String songAsString) {
        return songAsString.replace("#n#E#n#","#n#");
    }

    private static Song unsafeConvert(String songAsString) {
        // 0    1       2       3       4           5
        // 329#n#Hello#n#Adele#n#25#n#18 days ago#n#4:55
        String[] songArr = songAsString.split("#n#");
        List<Integer> minsAndSecs = Arrays.stream(songArr[5]
                        .split(":"))
                .map(Integer::valueOf).collect(Collectors.toList());
        int secs = minsAndSecs.get(0) * 60 + minsAndSecs.get(1);
        int id = Integer.valueOf(songArr[0]);
        Song theSong = new Song(id, songArr[1], songArr[2], songArr[3], secs);
        return theSong;
    }

    public static void SetDelimiter(String delimiter) {
        Song.delimiter=delimiter;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", durationSecs=" + durationSecs +
                '}';
    }
}
