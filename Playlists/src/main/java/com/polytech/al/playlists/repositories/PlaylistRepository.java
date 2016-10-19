package com.polytech.al.playlists.repositories;

import com.polytech.al.playlists.data.Playlist;
import com.polytech.al.playlists.data.Zone;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by user on 19/10/16.
 */
public interface PlaylistRepository extends MongoRepository<Playlist,String> {
    //List<Playlist> findPlaylistByZone(Zone zone);
}
