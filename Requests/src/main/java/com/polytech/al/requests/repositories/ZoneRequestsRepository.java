package com.polytech.al.requests.repositories;

import com.polytech.al.requests.data.ZoneRequests;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by user on 19/10/16.
 */
public interface ZoneRequestsRepository extends MongoRepository<ZoneRequests,String> {
    List<ZoneRequests> findZoneRequestsByZoneId(String zoneId);
}
