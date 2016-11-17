package com.polytech.al.zones.service;

import com.polytech.al.zones.dao.IZoneFinder;
import com.polytech.al.zones.dao.IZoneFinderMongo;
import com.polytech.al.zones.dao.ZoneFinderImplem;
import com.polytech.al.zones.data.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hraf on 02/11/16.
 */


@RestController
public class ZonesSeviceImpl implements ZonesService {

//    @Autowired
    private IZoneFinder zoneFinder = new ZoneFinderImplem();
    @Autowired
    private IZoneFinderMongo zoneFinderMongo;


    @RequestMapping("/zones")
    public String getZone(@RequestParam(value="latitude") float latitude, @RequestParam(value="longitude")float longitude) {
        return zoneFinder.findByCoordinates(latitude,longitude).getId().toString();
    }

    @RequestMapping("/zonesMango")
    public String getZoneMango(@RequestParam(value="latitude") float latitude, @RequestParam(value="longitude")float longitude) {
        return zoneFinder.findByCoordinates(latitude,longitude).getId().toString();
    }



//    @PostMapping("/zonesMango")
    @RequestMapping(method= RequestMethod.POST, name="/zonesMango")
    public String addZoneMango(@RequestBody Zone zone) {
            return zone.toString();
    }



}
