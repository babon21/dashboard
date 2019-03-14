package ru.eltex.app.dashboard.util;

import com.jayway.jsonpath.JsonPath;
import ru.eltex.app.dashboard.exception.ApiException;
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
    private static String convertWind(String wind) {
        double windValue = Double.parseDouble(wind);
        windValue *= 0.28;
        windValue = (double) Math.round(windValue * 10) / 10;
        return String.valueOf(windValue);
    }

    /**
     * Преобразование давления из милли баров в мм рт.ст.
     * @param pressure
     * @return давление в мм рт.ст.
     */
    private static String convertPressure(String pressure) {
        double pressureValue = Double.parseDouble(pressure);

        //перевод из милли бар в мм рт.ст.
        pressureValue *= 0.750064;

        //округление до целого
        return String.valueOf((int) Math.round(pressureValue));
    }

    private static final String ERROR_MESSAGE = "API был изменен, ошибка приведения типов";

    /**
     * Получение объекта Weather, парсинг json
     * @param json Строка, в формате json, содержащая информацию о погоде
     * @return Объект, содержащий информацию о погоде
     */
    public static Weather getWeather(String json) throws ApiException {
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

        //проверка полученных данных на соответствие типов
        if (!ValueHelper.checkFloat(cur_t) || !ValueHelper.checkFloat(feelsC)
                || !ValueHelper.checkFloat(humidity)
                || !ValueHelper.checkDouble(wind) || !ValueHelper.checkDouble(pressure)
                || !ValueHelper.checkFloat(tomorMinC) || !ValueHelper.checkFloat(tomorMaxC)
                || !ValueHelper.checkFloat(afternoon) || !ValueHelper.checkFloat(evening))
            throw new ApiException(ERROR_MESSAGE);

        weather.setCurC(cur_t);
        weather.setFeelsLikeC(feelsC);
        weather.setHumidity(humidity);
        weather.setDesc(desc);

        wind =  WeatherHelper.convertWind(wind);
        weather.setWind(wind);

        pressure = WeatherHelper.convertPressure(pressure);
        weather.setPressure(pressure);

        weather.setMinC(tomorMinC);
        weather.setMaxC(tomorMaxC);

        weather.setAfternoon(afternoon);
        weather.setAfternoonDesc(afternoonDesc);
        weather.setEvening(evening);
        weather.setEveningDesc(eveningDesc);

        return weather;
    }
}
