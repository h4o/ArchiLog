package com.polytech.al.playlists.repositories;

import com.polytech.al.playlists.data.Zone;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by user on 19/10/16.
 */
public interface ZoneRepository extends MongoRepository<Zone, String> {
}
