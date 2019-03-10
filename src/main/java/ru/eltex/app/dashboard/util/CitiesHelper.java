package ru.eltex.app.dashboard.util;

/**
 * Класс помощник, преобразующий название города из русского в англ.
 * @author darzhain
 */
public class CitiesHelper {

    public static String getCity(String city) {
        if (city.equals("Москва"))
            return "Moscow";
        else if(city.equals("Новосибирск"))
            return "Novosibirsk";
        else if (city.equals("Санкт-Петербург"))
            return "Saint-Petersburg";
        return "Новосибирск";
    }
}
