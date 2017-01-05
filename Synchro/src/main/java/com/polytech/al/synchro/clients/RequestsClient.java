package com.polytech.al.synchro.clients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

/**
 * Created by user on 04/01/17.
 */
@FeignClient("REQUESTS")
public interface RequestsClient {
    @RequestMapping(value="/zone/{zoneId}/")
    HashMap<String,Object> getRequestsForZone(@RequestParam("zoneId") String zoneId);
}
