package com.polytech.al.playlists.data;

/**
 * Created by user on 14/11/16.
 */

public class Song {
    private String id;
    private long length;

    public Song(String id, long length) {
        this.id = id;
        this.length = length;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }
}
