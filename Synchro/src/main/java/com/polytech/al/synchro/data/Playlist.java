package com.polytech.al.synchro.data;


import java.util.List;

/**
 * Created by user on 19/10/16.
 */
public class Playlist {

    private String id;

    private List<String> songs;


    public Playlist(String id, List<String> songs){
        this.id = id;
        this.songs = songs;
    }


    public List<String> getSongs() {
        return songs;
    }

    public void setSongs(List<String> songs) {
        this.songs = songs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
