package com.polytech.al.synchro.data;

/**
 * Created by user on 28/10/16.
 */
public class SynchroObject {
    private int position;
    private float time;
    private Playlist playlist;

    public SynchroObject( float time,int position, Playlist playlist) {

        this.time = time;
        this.position = position;
        this.playlist = playlist;
    }

    public SynchroObject(){

    }

    public float getTime() {
        return time;
    }

    public void setTime(Float time) {
        this.time = time;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
}
