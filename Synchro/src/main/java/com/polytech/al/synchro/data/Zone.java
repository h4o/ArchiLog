package com.polytech.al.synchro.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by user on 19/10/16.
 */
public class Zone {

    private String id;
    private String name;
    private float longitute;
    private float latitude;
    private Playlist playlists;


    public Zone(String id, String name, float longitute, float latitude, Playlist playlists) {
        this.id = id;
        this.name = name;
        this.longitute = longitute;
        this.latitude = latitude;
        this.playlists = playlists;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLongitute() {
        return longitute;
    }

    public void setLongitute(float longitute) {
        this.longitute = longitute;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public Playlist getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Playlist playlists) {
        this.playlists = playlists;
    }
}
