package com.polytech.al.requests.data;

/**
 * Created by user on 14/11/16.
 */

public class Song {
    private String id;
    private int iteration;
    private float length;
    private int positionAfter;

    public Song(){

    }

    public Song(String id,float length, int iteration) {
        this.id = id;
        this.length = length;
        this.iteration = iteration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public int getPositionAfter() {
        return positionAfter;
    }

    public void setPositionAfter(int positionAfter) {
        this.positionAfter = positionAfter;
    }

    public String toString(){
        return "";
    }
}
