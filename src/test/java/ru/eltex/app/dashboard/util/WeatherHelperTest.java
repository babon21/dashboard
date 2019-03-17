package ru.eltex.app.dashboard.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class WeatherHelperTest {

    @Test
    public void getWeather() {
        String json = "{\n" +
                "\t\"data\": {\n" +
                "\t\t\"current_condition\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"temp_C\": \"35\",\n" +
                "\t\t\t\t\"FeelsLikeC\": \"34\",\n" +
                "\t\t\t\t\"humidity\": \"55\",\n" +
                "\t\t\t\t\"lang_ru\": [\n" +
                "\t\t\t\t\t{\"value\":\"Нормально\"}\n" +
                "\t\t\t\t],\n" +
                "\t\t\t\t\"windspeedKmph\":\"12\",\n" +
                "\t\t\t\t\"pressure\":\"76\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"weather\":[\n" +
                "\t\t\t{},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"mintempC\":\"24\",\n" +
                "\t\t\t\t\"maxtempC\":\"52\",\n" +
                "\t\t\t\t\"hourly\":[\n" +
                "\t\t\t\t\t{},\n" +
                "\t\t\t\t\t{},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"tempC\": \"34\",\n" +
                "\t\t\t\t\t\t\"lang_ru\": [\n" +
                "\t\t\t\t\t\t\t{\"value\":\"Нормально\"}\n" +
                "\t\t\t\t\t\t]\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"tempC\": \"88\",\n" +
                "\t\t\t\t\t\t\"lang_ru\": [\n" +
                "\t\t\t\t\t\t\t{\"value\":\"Нормально\"}\n" +
                "\t\t\t\t\t\t]\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t]\n" +
                "\t\t\t},\n" +
                "\t\t]\n" +
                "\t}\n" +
                "}\n";
        WeatherHelper.getWeather(json);
    }
}