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

            String usd = JsonPath.read(jsonCbr, "$.Valute.USD.Value").toString();
            String usdPrev = JsonPath.read(jsonCbr, "$.Valute.USD.Previous").toString();

            String eur = JsonPath.read(jsonCbr, "$.Valute.EUR.Value").toString();
            String eurPrev = JsonPath.read(jsonCbr, "$.Valute.EUR.Previous").toString();

            float usdDiff = Float.parseFloat(usd) - Float.parseFloat(usdPrev);
            usdDiff = (float) Math.round(usdDiff * 1000) / 1000;
            float eurDiff = Float.parseFloat(eur) - Float.parseFloat(eurPrev);
            eurDiff = (float) Math.round(eurDiff * 1000) / 1000;

            currency.setUsd(usd);
            currency.setUsdDiff(usdDiff);

            currency.setEur(eur);
            currency.setEurDiff(eurDiff);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currency;
    }
}
