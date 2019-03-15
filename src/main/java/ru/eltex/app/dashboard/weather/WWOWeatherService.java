package ru.eltex.app.dashboard.weather;

import org.apache.log4j.Logger;
import ru.eltex.app.dashboard.exception.ApiException;
import ru.eltex.app.dashboard.util.WeatherHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Сервис получения прогноза погоды с worldweatheronline.com
 * @author darzhain
 */
public class WWOWeatherService implements WeatherService {

    /** Уникальный ключ, предоставляемый при регистрации на WWO
     *  Необходим для запроса
     * */
    private static final String key = "94ec3a6e682444b480f154701192602";

    private static final String baseUrl = "http://api.worldweatheronline.com/premium/v1/weather.ashx?";

    private static final Logger logger = Logger.getLogger(WWOWeatherService.class);

    /**
     * Получение прогноза погоды заданного города city
     * @param city Город, на английском
     * @return Информация о прогнозе погоды
     * @throws
     */
    public Weather getWeatherData(String city) throws ApiException, IOException {
        URL url;
        StringBuilder response = new StringBuilder();
        logger.info("Получение погоды города " + city);

        url = new URL( baseUrl + "key=" + key +"&q=" + city +
                    "&num_of_days=2&tp=6&format=json&mca=no&lang=ru");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream is;
        int status = connection.getResponseCode();
        if (status != HttpURLConnection.HTTP_OK)
            is = connection.getErrorStream();
        else
            is = connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;

        while((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();

        String json = response.toString();

        return WeatherHelper.getWeather(json);
    }
}
