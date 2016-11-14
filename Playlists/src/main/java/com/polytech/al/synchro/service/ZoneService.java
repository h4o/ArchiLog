package com.polytech.al.playlists.service;

import com.polytech.al.playlists.data.Playlist;
import com.polytech.al.playlists.data.Zone;
import com.polytech.al.playlists.repositories.PlaylistRepository;
import com.polytech.al.playlists.repositories.ZoneRepository;
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
@RequestMapping("/zone")
public class ZoneService {
    @Autowired
    private ZoneRepository zoneRepository;
    @Autowired
    private PlaylistRepository playlistRepository;
    @RequestMapping(method = RequestMethod.GET,value = "")
    public List<Zone> get(){
        return zoneRepository.findAll();
    }


    @RequestMapping(method = RequestMethod.GET,value = "/{zoneId}")
    public Zone get(@PathVariable String zoneId){
        return zoneRepository.findOne(zoneId);
    }
    @RequestMapping(method = RequestMethod.GET,value = "/{zoneId}/playlist")
    public Playlist getPlaylist(@PathVariable String zoneId){
        return playlistRepository.findOne(zoneRepository.findOne(zoneId).getPlaylists().getId());
    }
}
