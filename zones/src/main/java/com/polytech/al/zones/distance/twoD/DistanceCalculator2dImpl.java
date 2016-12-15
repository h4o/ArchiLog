package com.polytech.al.zones.distance.twoD;

import com.polytech.al.zones.data.Coordinates;

/**
 * Created by hraf on 15/12/16.
 */
public class DistanceCalculator2dImpl implements IDistanceCalculator2D {


    @Override
    public double distance(Coordinates point1, Coordinates point2) {


        double dx = point1.getLatitude() - point2.getLatitude();
        double dy = point1.getLongitude()- point2.getLongitude();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
