package ru.eltex.app.dashboard.weather;

import org.apache.log4j.Logger;
import ru.eltex.app.dashboard.exception.UserException;
import ru.eltex.app.dashboard.util.WeatherHelper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WWOWeatherService {
    private static final String key = "94ec3a6e682444b480f154701192602";
    private static final Logger logger = Logger.getLogger(WeatherService.class);

    public static Weather getWeatherData(String city) throws UserException {
        URL url;
        StringBuffer response = new StringBuffer();
        logger.info("Получение погоды города " + city);
        try {
            url = new URL("http://api.worldweatheronline.com/premium/v1/weather.ashx?" +
                    "key=" + key +"&q=" + city + "&num_of_days=2&tp=6&format=json&mca=no&lang=ru");
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

        } catch (Exception e) {
            logger.info("Ошибка запроса погоды");
            throw new UserException("Ошибка запроса погоды");
        }

        String json = response.toString();

        return WeatherHelper.getWeather(json);
    }

}
