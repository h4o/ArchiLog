package com.polytech.al.requests.clients;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by user on 31/01/17.
 */
@FeignClient("MUSICSTORE")
public interface MusicStoreClient {
    @RequestMapping(value="/musicRequest/{musicId}")
    Map<String,Object> getMusicData(@PathVariable("musicId") String musicId);
}
