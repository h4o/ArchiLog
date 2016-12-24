package com.polytech.al.synchro.service;

import com.polytech.al.synchro.clients.PlaylistsClient;
import com.polytech.al.synchro.data.Playlist;
import com.polytech.al.synchro.data.SynchroObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.inject.Named;

/**
 * Created by Fabien VICENTE on 28/10/16.
 */
@RestController
public class SynchroService {
    @Autowired PlaylistsClient playlistsClient;

    @RequestMapping(name="/test/")
    public String test(){

        return "JE SUIS UN TEST<br \\>"+playlistsClient.getPlaylist("0");
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
        return new SynchroObject(remaining,position);
    }

}
