package ru.eltex.app.dashboard.weather;

import java.io.IOException;

/**
 * Интерфейс для службы получения погоды
 * @author darzhain
 */
public interface WeatherService {
    Weather getWeatherData(String city) throws IOException;
}
