package com.polytech.al.synchro.data;

/**
 * Created by user on 28/10/16.
 */
public class SynchroObject {
    private String URI;
    private String zoneName;
    private String musicUUID;
    private Long time;

    public SynchroObject(String uri, String zoneName, String musicUUID, Long time) {
        URI = uri;
        this.zoneName = zoneName;
        this.musicUUID = musicUUID;
        this.time = time;
    }

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getMusicUUID() {
        return musicUUID;
    }

    public void setMusicUUID(String musicUUID) {
        this.musicUUID = musicUUID;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
