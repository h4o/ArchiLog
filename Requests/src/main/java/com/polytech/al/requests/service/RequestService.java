package com.polytech.al.requests.service;

import com.polytech.al.requests.clients.SynchroClient;
import com.polytech.al.requests.clients.ZonesClient;
import com.polytech.al.requests.data.Song;
import com.polytech.al.requests.data.Synchro;
import com.polytech.al.requests.data.ZoneRequests;
import com.polytech.al.requests.repositories.ZoneRequestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import com.pusher.rest.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
/**
 * Created by user on 19/10/16.
 */
@RestController
@RequestMapping("/requests")
@ConfigurationProperties(prefix="pusher")
public class RequestService {
    @Autowired
    private ZoneRequestsRepository repository;


    @Autowired
    private SynchroClient synchro;

    @Autowired
    private ZonesClient zonesClient;


    @Value("${pusher.appId:not set}")
    private String appId;
    @Value("${pusher.key:not set}")
    private String key;
    @Value("${pusher.secret:not set}")
    private String secret;









    @RequestMapping(method = RequestMethod.GET,value = "/zone/{zoneId}/")
    public List<Song> get(@PathVariable String zoneId){
        try {
            return repository.findZoneRequestsByZoneId(zoneId).get(0).getSongs();
        } catch (Exception e){
            return new ArrayList<Song>();
        }
    }


    @RequestMapping(method = RequestMethod.POST,value = "/add/")
    public void post(@RequestBody String musicName)
    {
        //first we request the song and its metadata, sadly we'll mock it for now
        Song s = new Song(musicName,new Random().nextInt(120)+60,0);//iteration is set to 0 for now
        //then we request information to synchro about what point in the playlist we are

        // GETing zone ID by genre, genre hardcoded for now
        String genre = "METAL";
        String zoneId = zonesClient.getZoneId(genre);
//        String zoneId = "0";

        Synchro synchroObject = synchro.getSynchro(zoneId);//TODO get the zone by genre (need genre from metadata before)
        s.setIteration(synchroObject.iteration);

        s.setPositionAfter(new Random().nextInt(synchroObject.playlist.songs.size()));

        List<ZoneRequests> zoneRequestsList = repository.findZoneRequestsByZoneId(zoneId);
        if(zoneRequestsList.isEmpty()){
            List<Song> songs = new ArrayList<Song>();
            songs.add(s);
            ZoneRequests zoneRequests = new ZoneRequests(null,songs,zoneId);
            repository.save(zoneRequests);
        }else{
        ZoneRequests zoneRequests = repository.findZoneRequestsByZoneId(zoneId).get(0);
        zoneRequests.getSongs().add(s);
        repository.save(zoneRequests);
        //then we add (randomly for now) the song


        //then we push a message to the streamers to get them to update
        System.out.println(appId+key+secret);
        Pusher pusher = new Pusher(appId, key, secret);
        pusher.setCluster("eu");
        pusher.setEncrypted(true);

        pusher.trigger("my-channel", "my-event", Collections.singletonMap("zone", zoneId));

        return;
        }
    }

}
