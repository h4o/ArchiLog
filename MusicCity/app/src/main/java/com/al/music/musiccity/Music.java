package com.al.music.musiccity;

import java.util.List;

/**
 * Created by user on 28/01/2017.
 */

public class Music {

    public String _id;
    public String name;
    public String artist;
    public String length;
    public String bitrate;
    public String genre;

    public String get_id() {
        return _id;
    }

    public String getArtist() {
        return artist;
    }

    public String getName() {
        return name;
    }

    public String getLength() {
        return length;
    }

    public String getBitrate() {
        return bitrate;
    }

    public String getGenre() {
        return genre;
    }
}
