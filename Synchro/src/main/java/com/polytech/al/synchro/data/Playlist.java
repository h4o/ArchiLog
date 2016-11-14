package com.polytech.al.synchro.data;


import java.util.List;

/**
 * Created by user on 19/10/16.
 */
public class Playlist {

    private String id;

    private List<Song> songs;


    private long length;

    public Playlist(String id, List<Song> songs){
        this.id = id;
        this.songs = songs;
        computeLength();
    }
    public Playlist(){

    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
        computeLength();
    }

    private void computeLength(){
        length = 0;
        for(Song s: songs){
            length += s.getLength();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public long getLength(){
        if(length == 0 && songs.size() != 0)
            computeLength();
        return length;
    }
}
