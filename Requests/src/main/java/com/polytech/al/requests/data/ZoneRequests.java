package com.polytech.al.requests.data;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by user on 19/10/16.
 */
@Document
public class ZoneRequests {
    @Id
    private String id;

    private List<Song> songs;

    private String zoneId;

    public ZoneRequests(String id, List<Song> songs, String zoneId){
        this.id = id;
        this.songs = songs;
        this.zoneId = zoneId;
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

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }
}
