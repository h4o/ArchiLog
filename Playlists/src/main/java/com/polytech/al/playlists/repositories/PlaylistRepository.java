package com.polytech.al.playlists.repositories;

import com.polytech.al.playlists.data.Playlist;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by user on 19/10/16.
 */
public interface PlaylistRepository extends MongoRepository<Playlist,String> {
    //List<Playlist> findPlaylistByZone(Zone zone);
}
