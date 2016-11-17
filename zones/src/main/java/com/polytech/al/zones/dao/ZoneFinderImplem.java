package com.polytech.al.zones.dao;

import com.polytech.al.zones.data.Zone;


/**
 * Created by hraf on 16/11/16.
 */
public class ZoneFinderImplem implements IZoneFinder {
    @Override
    public Zone findByCoordinates(float latitude, float longitude) {
        // mocking the database access
        if(String.valueOf(latitude).startsWith("1"))
            return new Zone("0");
        return new Zone("1");
    }


}
