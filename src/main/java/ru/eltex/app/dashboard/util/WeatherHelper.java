package ru.eltex.app.dashboard.util;

import com.jayway.jsonpath.JsonPath;
import ru.eltex.app.dashboard.weather.Weather;

import java.util.ArrayList;
import java.util.List;

/**
 * Основной класс(layout), содержащий все остальные пользовательские компоненты
 * @author darzhain
 */
public class WeatherHelper {
    public final static String DEGREE = "\u00b0";

    /**
     * Преобразование ветра из км/ч в м/с
     * @param wind значения ветра в км/ч
     * @return значение ветра в м/с
     */
    private static float convertWind(float wind) {
        float windValue = wind * 0.28f;
        windValue = (float) Math.round(windValue * 10) / 10;
        return windValue;
    }

    /**
     * Преобразование давления из милли баров в мм рт.ст.
     * @param pressure значение давления в милли барах
     * @return значение давления в мм рт.ст.
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
     * @throws IllegalArgumentException если json содержит невалидные данные
     */
    public static Weather getWeather(String json) throws IllegalArgumentException {
        Weather weather = new Weather();
        //12 элементов
        List<String> list = parseJsonWeather(json);

        int curValue = Integer.parseInt(list.get(0));
        int feelsCValue = Integer.parseInt(list.get(1));
        int humidityValue = Integer.parseInt(list.get(2));

        float windValue = Float.parseFloat(list.get(3));
        float pressureValue = Float.parseFloat(list.get(4));

        int tomorMinCValue = Integer.parseInt(list.get(5));
        int tomorMaxCValue = Integer.parseInt(list.get(6));

        int afternoonValue = Integer.parseInt(list.get(7));
        int eveningValue = Integer.parseInt(list.get(8));

        weather.setDesc(list.get(9));
        weather.setAfternoonDesc(list.get(10));
        weather.setEveningDesc(list.get(11));

        weather.setCurTemp(curValue);
        weather.setFeelsLikeC(feelsCValue);
        weather.setHumidity(humidityValue);

        windValue =  convertWind(windValue);
        weather.setWind(windValue);

        pressureValue = convertPressure(pressureValue);
        weather.setPressure(pressureValue);

        weather.setMinC(tomorMinCValue);
        weather.setMaxC(tomorMaxCValue);

        weather.setAfternoon(afternoonValue);
        weather.setEvening(eveningValue);

        return weather;
    }

    /**
     * Получение списка строк, содержащий информации о погоде
     * @param json строка, содержащая json прогноза погоды
     * @return список list, где
     * list[0]: текущая темп-ра, list[1]: ощущение темп-ры, list[2]: влажность,
     * list[3]: скорость ветра, list[4]: давление, list[5]: мин температура,
     * list[6]: макс темп-ра, list[7]: темп-ра после обеда,
     * list[8]: темп-ра вечером, list[9]: описание погоды
     * list[10]: погода после обеда, list[11]: погода вечером
     */
    private static List<String> parseJsonWeather(String json) {
        List<String> list = new ArrayList<>();

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

        list.add(cur_t);
        list.add(feelsC);
        list.add(humidity);

        list.add(wind);
        list.add(pressure);

        list.add(tomorMinC);
        list.add(tomorMaxC);

        list.add(afternoon);
        list.add(evening);

        list.add(desc);
        list.add(afternoonDesc);
        list.add(eveningDesc);

        return list;
    }
}
