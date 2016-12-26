package com.polytech.al.zones.dao;
import org.junit.BeforeClass;

/**
 * Created by user on 26/12/16.
 */
public class TestApplicationConfiguration {
    @BeforeClass
    public static void initConstants(){
        System.setProperty("SELF_URL", "foo");
        System.setProperty("NON_SECURE_PORT", "0");
        System.setProperty("HTTP_PROTOCOL", "foo");
        System.setProperty("EUREKA_URL", "foo");
    }
}
