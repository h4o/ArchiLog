package com.polytech.al.synchro.data;

/**
 * Created by user on 28/10/16.
 */
public class SynchroObject {
    private int position;
    private float time;
    private Playlist playlist;
    private int iteration;

    public SynchroObject( float time,int position, Playlist playlist, int iteration) {

        this.time = time;
        this.position = position;
        this.playlist = playlist;
        this.iteration = iteration;
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

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }
}
