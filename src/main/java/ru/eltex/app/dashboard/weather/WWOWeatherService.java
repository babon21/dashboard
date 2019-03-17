package ru.eltex.app.dashboard.weather;

import org.apache.log4j.Logger;
import ru.eltex.app.dashboard.util.WeatherHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public Weather getWeatherData(String city) throws IOException, IllegalArgumentException {
        logger.info("Получение погоды города " + city);

        URL url = getURL(city);

        String json = getWeatherJson(url);

        return WeatherHelper.getWeather(json);
    }

    private String getWeatherJson(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        StringBuilder response = new StringBuilder();
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

        return response.toString();
    }

    private URL getURL(String city) throws MalformedURLException {
        return new URL( baseUrl + "key=" + key +"&q=" + city +
                "&num_of_days=2&tp=6&format=json&mca=no&lang=ru");
    }
}
