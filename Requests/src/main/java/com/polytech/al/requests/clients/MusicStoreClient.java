package com.polytech.al.requests.clients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by user on 31/01/17.
 */
@FeignClient("MUSICSTORE")
public interface MusicStoreClient {
    @RequestMapping(value="/musicRequest/{musicId}")
    String getMusicData(@PathVariable("musicId") String musicId);
}
