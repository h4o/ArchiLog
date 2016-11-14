package com.polytech.al.playlists.data;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by user on 19/10/16.
 */
@Document
public class Playlist {
    @Id
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
