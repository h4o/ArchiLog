package com.polytech.al.requests.clients;

import com.polytech.al.requests.data.Synchro;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by user on 28/12/16.
 */
@FeignClient("SYNCHRO")
public interface SynchroClient {
    @RequestMapping(value="synchroZone/{zone}", method = RequestMethod.GET)
    Synchro getSynchro(@RequestParam("zone") String zone);
}
