package com.polytech.al.zones.data;

import java.io.Serializable;

/**
 * Created by hraf on 17/11/16.
 */
public class Coordinates implements Serializable {
    private float longitude;
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
}
