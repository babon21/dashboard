package ru.eltex.app.dashboard.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс помощник, преобразующий название города из русского в англ.
 * @author darzhain
 */
public class CitiesHelper {

    private static Map<String, String> cities;

    static {
        cities = new HashMap<>();
        cities.put("Москва", "Moscow");
        cities.put("Новосибирск", "Novosibirsk");
        cities.put("Санкт-Петербург", "Saint-Petersburg");
    }


    public static String getCity(String city) {
        return cities.getOrDefault(city, "Novosibirsk");
    }
}
