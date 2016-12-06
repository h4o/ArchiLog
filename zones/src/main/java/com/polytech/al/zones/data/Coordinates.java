package com.polytech.al.zones.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by hraf on 17/11/16.
 */
public class Coordinates implements Serializable {
    @JsonProperty

    private float longitude;
    @JsonProperty

    private float latitude ;


    public float getLongitude() {
        return longitude;
    }
    public Coordinates() {}

    public Coordinates(float longitude, float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates coordinates = (Coordinates) o;

        return (coordinates.getLatitude() == this.getLatitude()
                &&
                coordinates.getLongitude() == this.getLongitude());

    }


    @Override
    public String toString() {
        return "Coordinates{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
