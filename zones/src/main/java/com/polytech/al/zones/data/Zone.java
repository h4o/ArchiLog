package com.polytech.al.zones.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by hraf on 02/11/16.
 */

@Document
public class Zone implements Serializable {
    private String id ;
    @Id
    @JsonProperty
    private Coordinates coordinates;




    public Zone(Coordinates coordinates) {
        this.coordinates=coordinates;
    }


    public Zone(Coordinates coordinates,String id) {
        this.coordinates=coordinates;
        this.id=id;
    }


    public Zone() {
    }
    public Zone(String id) {
        this.id=id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
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
                ", coordinates=" + coordinates +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Zone zone = (Zone) o;

        return zone.getCoordinates().equals(this.coordinates);

    }


}
