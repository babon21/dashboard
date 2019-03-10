package ru.eltex.app.dashboard.currency;

import com.jayway.jsonpath.JsonPath;
import org.apache.log4j.Logger;
import ru.eltex.app.dashboard.exception.UserException;
import ru.eltex.app.dashboard.util.JsonHelper;

public class CurrencyService {

    private static final String URL_C = "https://www.cbr-xml-daily.ru/daily_json.js";
    private static final Logger logger = Logger.getLogger(CurrencyService.class);


    public static Currency getCurrency() throws UserException {
        logger.info("Получение курса валют");
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
            logger.info("Ошибка запроса валюты");
            throw new UserException("Ошибка запроса валюты");
        }
        return currency;
    }
}
