package com.polytech.al.synchro.service;

import com.polytech.al.synchro.clients.PlaylistsClient;
import com.polytech.al.synchro.clients.RequestsClient;
import com.polytech.al.synchro.data.Playlist;
import com.polytech.al.synchro.data.Song;
import com.polytech.al.synchro.data.SongRequest;
import com.polytech.al.synchro.data.SynchroObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Fabien VICENTE on 28/10/16.
 */
@RestController
public class SynchroService {
    @Autowired PlaylistsClient playlistsClient;
    @Autowired RequestsClient requestsClient;

    private List<SongRequest> getRequestsForIteration(List<SongRequest> songRequests, int i){
        List<SongRequest> response = new ArrayList<SongRequest>();
        for(SongRequest sr : songRequests){
            if(sr.iteration == i)
                response.add(sr);
        }
        return response;
    }

    private float getRequestsLengthForIteration(List<SongRequest> songRequests, int i){
        songRequests = getRequestsForIteration(songRequests,i);
        float length = 0;
        for(SongRequest sr : songRequests){
            length += sr.length;
        }
        return length;
    }

    public void mergePlaylist(Playlist p, List<SongRequest> songRequests){
        for(SongRequest sr : songRequests){
            Song s = new Song();
            s.setLength(sr.length);
            s.setId(sr.id);
            p.getSongs().add(sr.position_after+1,s);
        }
    }

    @RequestMapping(method = RequestMethod.GET,value = "/synchroZone/{zoneId}")
    public SynchroObject get(@PathVariable String zoneId){
        Playlist p = playlistsClient.getPlaylist(zoneId);//we request the current playlist
        //TODO add requests for the day
        List<SongRequest> requests = requestsClient.getRequestsForZone(zoneId);
        //get the elapsed time between midnight and now, we assume that we start at midnight
        Calendar c = Calendar.getInstance();
        long now = c.getTimeInMillis();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        long passed = now - c.getTimeInMillis();
        float secondsPassed = passed;//this is in milliseconds now (TODO change variable name)
        long length = p.getLength()*1000;//get length in milliseconds
        int iteration = 0;//(int) (secondsPassed / length);//used by request
        float remaining = secondsPassed;
        while(remaining > (length + (getRequestsLengthForIteration(requests,iteration) * 1000))){

            remaining -= length + (getRequestsLengthForIteration(requests,iteration) * 1000);
            iteration++;
        }
        
        //float remaining = (secondsPassed % length)/1000;//remaining time in the current playlist (in seconds)
        int position = 0;
        mergePlaylist(p,getRequestsForIteration(requests,iteration));
        for(int i = 0; i < p.getSongs().size(); i++){
            if(remaining < (p.getSongs().get(i).getLength() * 1000)){
                position = i;
                break;
            }
            remaining -= (p.getSongs().get(i).getLength()*1000);
            position = i;
        }
        return new SynchroObject(remaining/1000,position,p,iteration);
    }



    @RequestMapping(method=RequestMethod.POST, name="/synchro/")
    public SynchroObject getSynchro(@RequestBody Playlist p){
        Calendar c = Calendar.getInstance();
        long now = c.getTimeInMillis();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        long passed = now - c.getTimeInMillis();
        float secondsPassed = passed;
        long length = p.getLength()*1000;
        float remaining = (secondsPassed % length)/1000;
        int position = 0;
        for(int i = 0; i < p.getSongs().size(); i++){
            if(remaining < p.getSongs().get(i).getLength()){
                position = i;
                break;
            }
            remaining -= p.getSongs().get(i).getLength();
        }
        return new SynchroObject(remaining,position,p,0);
    }

}
