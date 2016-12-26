package com.polytech.al.requests.service;

import com.polytech.al.requests.data.Song;
import com.polytech.al.requests.repositories.ZoneRequestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by user on 19/10/16.
 */
@RestController
@RequestMapping("/requests")
public class RequestService {
    @Autowired
    private ZoneRequestsRepository repository;
    @RequestMapping(method = RequestMethod.GET,value = "/{zoneId}/")
    public List<Song> get(@PathVariable String zoneId){
        return repository.findZoneRequestsByZoneId(zoneId).get(0).getSongs();
    }


    @RequestMapping(method = RequestMethod.POST,value = "/{zoneId}/{musicId}")
    public void post(@PathVariable String zoneId,@PathVariable String musicId)
    {
        //this is the tricky part
        return;
    }

}
