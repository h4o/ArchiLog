package com.polytech.al.synchro.clients;

import com.polytech.al.synchro.data.SongRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 04/01/17.
 */
@FeignClient("REQUESTS")
public interface RequestsClient {
    @RequestMapping(value="/requests/zone/{zoneId}/")
    List<SongRequest> getRequestsForZone(@RequestParam("zoneId") String zoneId);
}
