package com.polytech.al.zones.dao;

import com.polytech.al.zones.data.Zone;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by hraf on 16/11/16.
 */
public interface IZoneFinderMongo extends MongoRepository<Zone, String> {
}
