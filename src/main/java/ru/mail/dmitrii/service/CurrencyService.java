package ru.mail.dmitrii.service;

import com.jayway.jsonpath.JsonPath;
import ru.mail.dmitrii.entity.Currency;
import ru.mail.dmitrii.util.JsonHelper;

public class CurrencyService {
    private static final String URL_C = "https://www.cbr-xml-daily.ru/daily_json.js";


    public static Currency getCurrency() {
        Currency currency = new Currency();
        try {
            String jsonCbr = JsonHelper.jsonToString(URL_C);


            //currency.setName(JsonPath.read(jsonCbr, "$.Valute.USD.CharCode").toString() + "/RUB");
            currency.setUsd(JsonPath.read(jsonCbr, "$.Valute.USD.Value").toString());


            //eur.setName(JsonPath.read(jsonCbr, "$.Valute.EUR.CharCode").toString() + "/RUB");
            currency.setEur(JsonPath.read(jsonCbr, "$.Valute.EUR.Value").toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return currency;
    }
}
