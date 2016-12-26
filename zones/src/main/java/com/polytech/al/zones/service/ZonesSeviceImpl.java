package com.polytech.al.zones.service;

import com.polytech.al.zones.dao.IZoneFinderMongo;
import com.polytech.al.zones.data.Coordinates;
import com.polytech.al.zones.data.Genre;
import com.polytech.al.zones.data.Zone;
import com.polytech.al.zones.distance.twoD.DistanceCalculator2dImpl;
import com.polytech.al.zones.distance.twoD.IDistanceCalculator2D;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hraf on 02/11/16.
 */


@RestController
public class ZonesSeviceImpl implements ZonesService {

    //TODO:Autowired


    private IDistanceCalculator2D distanceCalculator2D = new DistanceCalculator2dImpl();
    @Autowired
    private IZoneFinderMongo zoneFinderMongo;


    

    @RequestMapping("/zones")
    public String getZone(@RequestParam(value="latitude") float latitude, @RequestParam(value="longitude")float longitude) {
        Coordinates clientCoordinates = new Coordinates(longitude,latitude);
        List<Zone> allZones = zoneFinderMongo.findAll();
        String ZoneId = allZones.get(0).getId();
        double minDistance = distanceCalculator2D.distance(allZones.get(0).getCenter(),clientCoordinates);
        for(Zone z : allZones){
            double distance = distanceCalculator2D.distance(z.getCenter(),clientCoordinates);
            if(distance < minDistance){
                minDistance = distance;
                ZoneId = z.getId();
            }
        }
     return ZoneId;
    }



    @PostMapping("/zones")
    public void addZone(@RequestBody Zone zone) {
        zoneFinderMongo.save(zone);
    }

    @RequestMapping(method = RequestMethod.GET,value = "zones/{zoneId}/genres")
    public List<Genre> getGenres(@PathVariable String zoneId) {

        return zoneFinderMongo.findOne(zoneId).getGenres();
    }

    @RequestMapping(method = RequestMethod.POST,value = "zones/{zoneId}/genres")
    public void addGenre(@PathVariable String zoneId, @RequestBody List<Genre> genres) {
        //TODO : do this properly
        //TODO using findOne
        List<Zone> all = zoneFinderMongo.findAll();
        Zone zone=null;
        for (Zone z :all) {
            if(z.getId().equals(zoneId))
                zone = z;
        }
        zone.getGenres().addAll(genres);
        zoneFinderMongo.save(zone);
    }


}
