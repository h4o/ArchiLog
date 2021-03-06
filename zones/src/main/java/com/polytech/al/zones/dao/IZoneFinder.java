package com.polytech.al.zones.dao;

import com.polytech.al.zones.data.Zone;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hraf on 16/11/16.
 */


public interface IZoneFinder  {
    Zone findByCoordinates(float latitude, float longitude);
}
