package com.polytech.al.zones.service;

import com.polytech.al.zones.dao.IZoneFinder;
import com.polytech.al.zones.dao.IZoneFinderMongo;
import com.polytech.al.zones.dao.ZoneFinderImplem;
import com.polytech.al.zones.data.Coordinates;
import com.polytech.al.zones.data.Zone;
import com.polytech.al.zones.distance.twoD.DistanceCalculator2dImpl;
import com.polytech.al.zones.distance.twoD.IDistanceCalculator2D;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hraf on 02/11/16.
 */


@RestController
public class ZonesSeviceImpl implements ZonesService {

    //TODO:Autowired


    private IDistanceCalculator2D distanceCalculator2D = new DistanceCalculator2dImpl();
    private IZoneFinder zoneFinder = new ZoneFinderImplem();
    @Autowired
    private IZoneFinderMongo zoneFinderMongo;



    @RequestMapping("/zones")
    public String getZone(@RequestParam(value="latitude") float latitude, @RequestParam(value="longitude")float longitude) {
        return zoneFinder.findByCoordinates(latitude, longitude).getId();
    }

    @RequestMapping("/zonesMango")
    public String getZoneMango(@RequestParam(value="latitude") float latitude, @RequestParam(value="longitude")float longitude) {
        Coordinates clientCoordinates = new Coordinates(longitude,latitude);
        List<Zone> allZones = zoneFinderMongo.findAll();
        String ZoneId = allZones.get(0).getId();
        double minDistance = distanceCalculator2D.distance(allZones.get(0).getCenter(),clientCoordinates);
        for(Zone z : allZones){
            double distance = distanceCalculator2D.distance(z.getCenter(),clientCoordinates);
            if(distance< minDistance){
                minDistance = distance;
                ZoneId = z.getId();
            }
        }
     return ZoneId;
//        return zoneFinder.findByCoordinates(latitude, longitude).getId();
    }



    @PostMapping("/zonesMango")
//    @RequestMapping(method= RequestMethod.POST, name="/zonesMango")
    public String addZoneMango(@RequestBody Zone zone) {
        zoneFinderMongo.save(zone);
        return ("=================>"+zone.getCenter().toString());
    }



}
