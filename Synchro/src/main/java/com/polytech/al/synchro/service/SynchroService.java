package com.polytech.al.synchro.service;

import com.polytech.al.synchro.clients.PlaylistsClient;
import com.polytech.al.synchro.data.SynchroObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabien VICENTE on 28/10/16.
 */
@RestController
public class SynchroService {
    @Autowired
    private PlaylistsClient playlistsClient;



    @RequestMapping("/synchro/")
    public List<String> getSynchro(){
        System.out.println("TETSTTEZTETETT");
        System.out.println(playlistsClient.getZones());
        return new ArrayList<String>();
    }

}
