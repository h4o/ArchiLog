package com.polytech.al.requests.service;

import com.polytech.al.requests.clients.SynchroClient;
import com.polytech.al.requests.clients.ZonesClient;
import com.polytech.al.requests.data.Song;
import com.polytech.al.requests.data.Synchro;
import com.polytech.al.requests.repositories.ZoneRequestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by user on 19/10/16.
 */
@RestController
@RequestMapping("/requests")
public class RequestService {
    @Autowired
    private ZoneRequestsRepository repository;


    @Autowired
    private SynchroClient synchro;

    @Autowired
    private ZonesClient zonesClient;









    @RequestMapping(method = RequestMethod.GET,value = "/zone/{zoneId}/")
    public List<Song> get(@PathVariable String zoneId){
        System.out.println("hello");
        return repository.findZoneRequestsByZoneId(zoneId).get(0).getSongs();
    }


    @RequestMapping(method = RequestMethod.POST,value = "/add/")
    public void post(@RequestBody String musicName)
    {
        //first we request the song and its metadata, sadly we'll mock it for now
        Song s = new Song(musicName,new Random().nextInt(120)+60,0);//iteration is set to 0 for now
        //then we request information to synchro about what point in the playlist we are

        // GETing zone ID by genre, genre hardcoded for now
        String genre = "METAL";
        String ZoneId = zonesClient.getZoneId(genre);

        System.out.println(ZoneId);
        Synchro synchroObject = synchro.getSynchro("0");//TODO get the zone by genre (need genre from metadata before)
        s.setIteration(synchroObject.iteration);

        s.setPositionAfter(new Random().nextInt(synchroObject.playlist.songs.size()));
        System.out.println(s);
        //then we add (randomly for now) the song
        return;
    }

}
