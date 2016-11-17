package com.polytech.al.zones.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * Created by hraf on 02/11/16.
 */

@Document
public class Zone {
    @Id
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


    @Override
    public String toString() {
        return "Zone{" +
                "id='" + id + '\'' +
                '}';
    }
}
