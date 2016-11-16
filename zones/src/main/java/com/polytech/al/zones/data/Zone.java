package com.polytech.al.zones.data;

import java.util.UUID;

/**
 * Created by hraf on 02/11/16.
 */
public class Zone {

    private String id ;

    public Zone() {
    }

    public Zone(String id) {
        this.id=id;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
