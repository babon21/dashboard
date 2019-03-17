package ru.eltex.app.dashboard.weather;

import org.apache.log4j.Logger;
import org.junit.Test;
import ru.eltex.app.dashboard.util.CitiesHelper;

import java.io.IOException;

import static org.junit.Assert.*;

public class WWOWeatherServiceTest {

    private static Logger logger = Logger.getLogger(WWOWeatherServiceTest.class);

    @Test
    public void getWeatherData() {
        WeatherService weatherService = new WWOWeatherService();
        String city = CitiesHelper.getCity("Москва");
        Weather weather = null;
        try {
            weather = weatherService.getWeatherData(city);
        } catch (IOException | IllegalArgumentException e) {
            logger.error("Failed test", e);
        }

        System.out.println(weather);
        assertNotNull(weather);
    }
}