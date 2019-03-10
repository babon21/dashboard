package ru.eltex.app.dashboard.weather;

import ru.eltex.app.dashboard.exception.UserException;

public interface WeatherService {
    Weather getWeatherData(String city) throws UserException;
}
