package ru.eltex.app.dashboard.util;

import com.jayway.jsonpath.JsonPath;
import ru.eltex.app.dashboard.weather.Weather;

/**
 * Основной класс(layout), содержащий все остальные пользовательские компоненты
 * @author darzhain
 */
public class WeatherHelper {
    public final static String DEGREE = "\u00b0";

    /**
     * Преобразование ветра из км/ч в м/с
     * @param wind
     * @return ветер в м/с
     */
    private static float convertWind(float wind) {
        float windValue = wind * 0.28f;
        windValue = (float) Math.round(windValue * 10) / 10;
        return windValue;
    }

    /**
     * Преобразование давления из милли баров в мм рт.ст.
     * @param pressure
     * @return давление в мм рт.ст.
     */
    private static int convertPressure(float pressure) {
        //перевод из милли бар в мм рт.ст.
        float pressureValue = pressure * 0.750064f;

        //округление до целого
        return Math.round(pressureValue);
    }


    /**
     * Получение объекта Weather, парсинг json
     * @param json Строка, в формате json, содержащая информацию о погоде
     * @return Объект, содержащий информацию о погоде
     * @throws NumberFormatException
     */
    public static Weather getWeather(String json) throws NumberFormatException {
        Weather weather = new Weather();

        String cur_t =  JsonPath.read(json, "$.data.current_condition[0].temp_C").toString();
        String feelsC =  JsonPath.read(json, "$.data.current_condition[0].FeelsLikeC").toString();
        String humidity =  JsonPath.read(json, "$.data.current_condition[0].humidity").toString();
        String desc = JsonPath.read(json, "$.data.current_condition[0].lang_ru[0].value");

        String wind = JsonPath.read(json, "$.data.current_condition[0].windspeedKmph").toString();
        String pressure = JsonPath.read(json, "$.data.current_condition[0].pressure").toString();

        String tomorMinC = JsonPath.read(json, "$.data.weather[1].mintempC").toString();
        String tomorMaxC = JsonPath.read(json, "$.data.weather[1].maxtempC").toString();

        String afternoon = JsonPath.read(json, "$.data.weather[1].hourly[2].tempC").toString();
        String afternoonDesc = JsonPath.read(json, "$.data.weather[1].hourly[2].lang_ru[0].value").toString();
        String evening = JsonPath.read(json, "$.data.weather[1].hourly[3].tempC").toString();
        String eveningDesc = JsonPath.read(json, "$.data.weather[1].hourly[3].lang_ru[0].value").toString();

        int curValue = Integer.parseInt(cur_t);
        int feelsCValue = Integer.parseInt(feelsC);
        int humidityValue = Integer.parseInt(humidity);

        float windValue = Float.parseFloat(wind);
        float pressureValue = Float.parseFloat(pressure);

        int tomorMinCValue = Integer.parseInt(tomorMinC);
        int tomorMaxCValue = Integer.parseInt(tomorMaxC);

        int afternoonValue = Integer.parseInt(afternoon);
        int eveningValue = Integer.parseInt(evening);

        weather.setCurTemp(curValue);
        weather.setFeelsLikeC(feelsCValue);
        weather.setHumidity(humidityValue);
        weather.setDesc(desc);

        windValue =  WeatherHelper.convertWind(windValue);
        weather.setWind(windValue);

        pressureValue = WeatherHelper.convertPressure(pressureValue);
        weather.setPressure(pressureValue);

        weather.setMinC(tomorMinCValue);
        weather.setMaxC(tomorMaxCValue);

        weather.setAfternoon(afternoonValue);
        weather.setAfternoonDesc(afternoonDesc);
        weather.setEvening(eveningValue);
        weather.setEveningDesc(eveningDesc);

        return weather;
    }
}
