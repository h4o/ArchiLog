package com.polytech.al.zones.service;

import com.polytech.al.zones.dao.IZoneFinder;
import com.polytech.al.zones.dao.ZoneFinderImplem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hraf on 02/11/16.
 */


@RestController
public class ZonesSeviceImpl implements ZonesService {

//    @Autowired
    private IZoneFinder zoneFinder = new ZoneFinderImplem();


    @RequestMapping("/zones")
    public String getZone(@RequestParam(value="latitude") float latitude, @RequestParam(value="longitude")float longitude) {
        return zoneFinder.findByCoordinates(latitude,longitude).getId().toString();
    }



}
