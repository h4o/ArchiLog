package com.polytech.al.playlists;

import com.polytech.al.playlists.repositories.PlaylistRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by user on 17/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {
        SampleSimpleApplication.class
})
public class PlaylistTest extends TestApplicationConfiguration {


    @Autowired
    private PlaylistRepository repository;

    @Test
    public void shouldHavePlaylists(){
        assertTrue(repository.findAll().size()>0);
    }

    @Test
    public void shouldHaveSongs(){
        assertTrue(repository.findOne("3").getSongs().size()>0);
    }
}
