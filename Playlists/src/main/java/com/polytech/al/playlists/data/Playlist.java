package com.polytech.al.playlists.data;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by user on 19/10/16.
 */
@Document
public class Playlist {
    @Id
    private String id;

    private List<Song> songs;


    public Playlist(String id, List<Song> songs){
        this.id = id;
        this.songs = songs;
    }


    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
