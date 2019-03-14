package ru.eltex.app.dashboard.weather;

import ru.eltex.app.dashboard.exception.ApiException;

import java.io.IOException;

/**
 * Интерфейс для службы получения погоды
 * @author darzhain
 */
public interface WeatherService {
    Weather getWeatherData(String city) throws IOException, ApiException;
}
