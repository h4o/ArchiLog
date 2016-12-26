package com.polytech.al.zones.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hraf on 02/11/16.
 */

@Document
public class Zone implements Serializable {
    @Id
    private String id ;
    private Coordinates center;
    private List<Genre> genres = new ArrayList<Genre>();





    public Zone(Coordinates center) {
        this.center=center;
    }


    public Zone(Coordinates coordinates,String id,List<Genre> genres) {
        this.center=coordinates;
        this.id=id;
        this.genres=genres;
    }


    public Zone() {
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
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
