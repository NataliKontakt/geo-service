package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public class LocalizationServiceImplTest {
    LocalizationServiceImpl localizationService;

    @BeforeEach
    public void setUp() {
        localizationService = new LocalizationServiceImpl();
    }

    @ParameterizedTest
    @MethodSource
    public void returnLocationWitCountryIpTest(Country country, String message) {
        String actual = localizationService.locale(country);
        Assertions.assertEquals(message, actual);
    }

    public static Stream<Arguments> returnLocationWitCountryIpTest() {
        return Stream.of(
                Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.BRAZIL, "Welcome"),
                Arguments.of(Country.GERMANY, "Welcome")
        );
    }
}
