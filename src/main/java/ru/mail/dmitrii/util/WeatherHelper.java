package ru.mail.dmitrii.util;

import com.jayway.jsonpath.JsonPath;
import ru.mail.dmitrii.entity.Weather;

public class WeatherHelper {
    public final static String DEGREE = "\u00b0";

    public static String convertWind(String wind) {
        double windValue = Double.parseDouble(wind);
        windValue *= 0.28;
        windValue = (double) Math.round(windValue * 10) / 10;
        return String.valueOf(windValue);
    }

    public static String convertPressure(String pressure) {
        double pressureValue = Double.parseDouble(pressure);

        //перевод из милли бар в мм рт.ст.
        pressureValue *= 0.750064;

        //округление до целого
        return String.valueOf((int) Math.round(pressureValue));
    }

    public static Weather getWeather(String json) {
        Weather weather = new Weather();

        String cur_t =  JsonPath.read(json, "$.data.current_condition[0].temp_C").toString();
        String feelsC =  JsonPath.read(json, "$.data.current_condition[0].FeelsLikeC").toString();
        String humidity =  JsonPath.read(json, "$.data.current_condition[0].humidity").toString();
        String desc = JsonPath.read(json, "$.data.current_condition[0].lang_ru[0].value");

        weather.setCurC(cur_t);
        weather.setFeelsLikeC(feelsC);
        weather.setHumidity(humidity);
        weather.setDesc(desc);

        String wind = JsonPath.read(json, "$.data.current_condition[0].windspeedKmph").toString();
        wind =  WeatherHelper.convertWind(wind);
        weather.setWind(wind);

        String pressure = JsonPath.read(json, "$.data.current_condition[0].pressure").toString();

        pressure = WeatherHelper.convertPressure(pressure);
        weather.setPressure(pressure);

        String tomorMinC = JsonPath.read(json, "$.data.weather[1].mintempC").toString();
        String tomorMaxC = JsonPath.read(json, "$.data.weather[1].maxtempC").toString();
        weather.setMin_C(tomorMinC);
        weather.setMax_C(tomorMaxC);

        return weather;
    }
}
