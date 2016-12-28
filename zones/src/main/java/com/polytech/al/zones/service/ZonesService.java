package com.polytech.al.zones.service;


import com.polytech.al.zones.data.Genre;
import com.polytech.al.zones.data.Zone;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by hraf on 28/10/16.
 */
public interface ZonesService {


     String getZone(float latitude, float longitude);

     void addZone(Zone zone);

     List<Genre> getGenres(String zoneID);

     void addGenre(String zoneId, List<Genre> genres);

     List<Zone> getZoneByGenre(Genre genre);


}



