package com.polytech.al.requests.clients;

import com.polytech.al.requests.data.Genres;
import com.polytech.al.requests.data.Synchro;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by hraf on 29/12/16.
 */


@FeignClient("zones")
public interface  ZonesClient {


    @RequestMapping(value="zones/{genre}", method = RequestMethod.GET)
    String getZoneId(@PathVariable("genre") String genre);

    @RequestMapping(value = "genres")
    Genres getAllGenres();


}
