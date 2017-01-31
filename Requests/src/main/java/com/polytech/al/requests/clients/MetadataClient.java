package com.polytech.al.requests.clients;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.polytech.al.requests.data.Synchro;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by hraf on 28/01/17.
 */


@FeignClient(url="al-metadata.herokuapp.com",name="test")
public interface MetadataClient {
    @RequestMapping(value="/synchroZone/0/",method= RequestMethod.GET)
    String test();

    @RequestMapping(value="/search?track={trackName}&artist={artistName}", method = RequestMethod.GET)
    Map<String,Object> getMetadata(@RequestParam("trackName") String trackName, @RequestParam("artistName")String artistName);


//    @RequestLine("GET /domains/{domainId}/records?name={name}")
//    Response getMetadata(@Param("domainId") int id, @Param("name") String nameFilter,
//                                  @QueryMap Map<String, String> options);
}
