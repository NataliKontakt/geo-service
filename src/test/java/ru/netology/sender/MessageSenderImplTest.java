package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;


import java.util.HashMap;
import java.util.Map;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

public class MessageSenderImplTest {
    public static MessageSender messageSender;

    @Test
    public void sendsRussianTextToRussianIPtest() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Location mockLocation = new Location("Moscow", RUSSIA, null, 0);
        Mockito.when(geoService.byIp(Mockito.startsWith("172.")))
                .thenReturn(mockLocation);

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(RUSSIA))
                .thenReturn("Добро пожаловать");
        messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");

        Assertions.assertEquals("Добро пожаловать", messageSender.send(headers));
    }

    @Test
    public void sendsAmericanTextToAmericanIPtest() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Location mockLocation = new Location("New York", USA, null, 0);
        Mockito.when(geoService.byIp(Mockito.startsWith("96.")))
                .thenReturn(mockLocation);

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(USA))
                .thenReturn("Welcome");
        messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.123.12.19");

        Assertions.assertEquals("Welcome", messageSender.send(headers));
    }

}
