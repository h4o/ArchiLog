package com.polytech.al.zones.dao;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by hraf on 17/11/16.
 */
public class ZoneFinderImplemTest {

    private  ZoneFinderImplem zoneFinderImplem ;
    @Before
    public  void setUp(){
        this.zoneFinderImplem = new ZoneFinderImplem();
    }

    @Test
    public void findByCoordinatesTest(){
        String zoneId = zoneFinderImplem.findByCoordinates(1232434,34343434).getId();
        assertEquals(zoneId,"123456789");
         zoneId = zoneFinderImplem.findByCoordinates(2332434,34343434).getId();
        assertEquals(zoneId,"987654321");




    }





}
