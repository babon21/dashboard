package ru.eltex.app.dashboard.weather;

import ru.eltex.app.dashboard.exception.UserException;

/**
 * Интерфейс для службы получения погоды
 * @author darzhain
 */
public interface WeatherService {
    Weather getWeatherData(String city) throws UserException;
}
