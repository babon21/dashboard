package ru.mail.dmitrii.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService {
    private static String key = "94ec3a6e682444b480f154701192602";

    public String getWeatherData() {
        String city = "Novosibirsk";
        String country = "";
        URL url;
        StringBuffer response = new StringBuffer();
        /*url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + country + "&units="
                + location.getUnit() + "&mode=xml&APPID="+appID );*/
        try {
            url = new URL("http://api.worldweatheronline.com/premium/v1/weather.ashx?" +
                    "key=" + key +
                    "&q=" + city +
                    "&num_of_days=3" +
                    "&tp=24&format=json&mca=no");


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
            e.printStackTrace();
        }
        return response.toString();
    }

}
