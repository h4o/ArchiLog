package com.polytech.al.zones.distance.twoD;

import com.polytech.al.zones.data.Coordinates;
import org.junit.Before;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hraf on 28/12/16.
 */
public class DistanceCalculator2dImplTest {

    private DistanceCalculator2dImpl distanceCalculator2d;
    /*
        tolerance
     */
    private static final float DELTA = 0;


    @Before
    public  void setUp(){
        this.distanceCalculator2d = new DistanceCalculator2dImpl();
    }


    @Test
    public void testDistance(){
        Coordinates a  = new Coordinates(0,0);
        Coordinates b  = new Coordinates(0,0);
        assertEquals(distanceCalculator2d.distance(a,b),0,DELTA);

        b = new Coordinates(2,0);
        assertEquals(distanceCalculator2d.distance(a,b),2,DELTA);

        b = new Coordinates(-2,0);
        assertEquals(distanceCalculator2d.distance(a,b),2,DELTA);

        b = new Coordinates(0,4);
        assertEquals(distanceCalculator2d.distance(a,b),4,DELTA);

        b = new Coordinates(-4,0);
        assertEquals(distanceCalculator2d.distance(a,b),4,DELTA);


        b = new Coordinates(5,5);
        assertEquals(distanceCalculator2d.distance(a,b),Math.sqrt(50),DELTA);

        b = new Coordinates(-5,-5);
        assertEquals(distanceCalculator2d.distance(a,b),Math.sqrt(50),DELTA);




        b = new Coordinates(1,1);
        a = new Coordinates(1,5);

        assertEquals(distanceCalculator2d.distance(a,b),4,DELTA);

    }



}
