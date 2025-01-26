package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class GeoServiceImplTest {
    GeoService geoService;
    String id;

    @BeforeEach
    public void setUp() {
        geoService = new GeoServiceImpl();
    }

    @Test
    public void returnRussianLocationWitRussianIpTest() {
        id = "172.0.32.11";
        Location location = geoService.byIp(id);
        Assertions.assertEquals(Country.RUSSIA, location.getCountry());
        Assertions.assertEquals("Moscow", location.getCity());
    }

    @Test
    public void returnAmericanLocationWitAmericanIpTest() {
        id = "96.44.183.149";
        Location location = geoService.byIp(id);
        Assertions.assertEquals(Country.USA, location.getCountry());
        Assertions.assertEquals("New York", location.getCity());
    }
}
