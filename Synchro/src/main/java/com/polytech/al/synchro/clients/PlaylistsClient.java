package com.polytech.al.synchro.clients;

import com.polytech.al.synchro.data.Playlist;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
/**
 * Created by user on 04/11/16.
 */
@FeignClient("PLAYLISTS")
public interface PlaylistsClient {
    @RequestMapping(value="/zone/{zone}/playlist",method = RequestMethod.GET)
    Playlist getPlaylist(@RequestParam("zone") String zone);
}
