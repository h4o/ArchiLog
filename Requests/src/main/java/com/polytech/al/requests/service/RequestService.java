package com.polytech.al.requests.service;

import com.polytech.al.requests.clients.MetadataClient;
import com.polytech.al.requests.clients.MusicStoreClient;
import com.polytech.al.requests.clients.SynchroClient;
import com.polytech.al.requests.clients.ZonesClient;
import com.polytech.al.requests.data.Genres;
import com.polytech.al.requests.data.Song;
import com.polytech.al.requests.data.Synchro;
import com.polytech.al.requests.data.ZoneRequests;
import com.polytech.al.requests.repositories.ZoneRequestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.pusher.rest.*;

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


    @Autowired
    private MetadataClient metadataClient;

    @Autowired
    private MusicStoreClient musicStoreClient;


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


    @RequestMapping(method = RequestMethod.POST,value = "/add/",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String post(@RequestBody String musicName)
    {

        //first we request the song and its metadata, sadly we'll mock it for now
        //then we request information to synchro about what point in the playlist we are
        Map<String,Object> music = musicStoreClient.getMusicData(musicName);
        System.out.println(music);
        Map<String, Object> md = metadataClient.getMetadata((String)music.get("name"),(String)music.get("artist"));
        // GETing zone ID by genre, genre hardcoded for now
        Song s = new Song(musicName,Integer.parseInt((String)music.get("length")),0);//iteration is set to 0 for now

        Genres g = zonesClient.getAllGenres();
        System.out.println(g);
        List<String> genres = (List<String>)md.get("genres");
        String genreSelected = null;
        for(String metadataGenre : genres){
            if(g.genres.contains(metadataGenre.toUpperCase())){
                System.out.println("WE FOUND THE GENRE "+metadataGenre);
                genreSelected = metadataGenre.toUpperCase();
            }
        }
        if(genreSelected == null){
            return "\"KO\"";
        }


        String zoneId = zonesClient.getZoneId(genreSelected);

        Synchro synchroObject = synchro.getSynchro(zoneId);//TODO get the zone by genre (need genre from metadata before)
        List<Map<String,Object>> songMetadatas = new ArrayList<Map<String,Object>>();
        for(Song so : synchroObject.playlist.songs){
            Map<String,Object> mds = musicStoreClient.getMusicData(so.getId());
            if(mds != null)
                songMetadatas.add(metadataClient.getMetadata((String)mds.get("name"),(String)mds.get("artist")));
        }
        s.setIteration(synchroObject.iteration);

        s.setPositionAfter(synchroObject.position + new Random().nextInt(synchroObject.playlist.songs.size() - synchroObject.position));

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

        }


        //then we push a message to the streamers to get them to update
        System.out.println(appId+key+secret);
        Pusher pusher = new Pusher(appId, key, secret);
        pusher.setCluster("eu");
        pusher.setEncrypted(true);

        pusher.trigger("my-channel", "my-event", Collections.singletonMap("zone", zoneId));

        return "\"OK\"";
    }

}
