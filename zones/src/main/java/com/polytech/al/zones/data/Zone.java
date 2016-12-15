package com.polytech.al.zones.data;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by hraf on 02/11/16.
 */

@Document
public class Zone implements Serializable {
    private String id ;
//    @Id
//    @JsonProperty
    private Coordinates center;


//43.707031, 7.192997


    public Zone(Coordinates center) {
        this.center=center;
    }


    public Zone(Coordinates coordinates,String id) {
        this.center=coordinates;
        this.id=id;
    }


    public Zone() {
    }
    public Zone(String id) {
        this.id=id;
    }

    public Coordinates getCenter() {
        return center;
    }

    public void setCenter(Coordinates coordinates) {
        this.center = coordinates;
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
                ", coordinates=" + center +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Zone zone = (Zone) o;

        return zone.getCenter().equals(this.center);

    }


}
