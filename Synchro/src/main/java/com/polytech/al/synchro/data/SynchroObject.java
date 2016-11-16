package com.polytech.al.synchro.data;

/**
 * Created by user on 28/10/16.
 */
public class SynchroObject {
    private int position;
    private Long time;

    public SynchroObject( Long time,int position) {

        this.time = time;
        this.position = position;
    }

    public SynchroObject(){

    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
