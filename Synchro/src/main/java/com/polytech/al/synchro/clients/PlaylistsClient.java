package com.polytech.al.synchro.clients;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.cloud.netflix.feign.FeignClient;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 04/11/16.
 */
@FeignClient("PLAYLISTS")
public interface PlaylistsClient {
    @RequestMapping(value="/zone",method = RequestMethod.GET)
    List<Map> getZones();
}
