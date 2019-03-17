package ru.eltex.app.dashboard.currency;

import org.apache.log4j.Logger;
import ru.eltex.app.dashboard.util.CurrencyHelper;
import ru.eltex.app.dashboard.util.JsonHelper;

import java.io.IOException;
import java.util.List;


/**
 * Получение и парсинг курса валют с сайта Центрального Банка России
 *
 * @author darzhain
 */
public class CBRCurrencyService implements CurrencyService {

    private CurrencyHelper helper;

    /**
     * URL запроса к API сервиса, предоставляющий данные о курсе валют
     * в формате JSON
     **/
    private static final String URL = "https://www.cbr-xml-daily.ru/daily_json.js";

    private static final Logger logger = Logger.getLogger(CBRCurrencyService.class);

    public CBRCurrencyService(CurrencyHelper helper) {
        this.helper = helper;
    }

    /**
     * Получить данные о курсе валют
     * @return Информацию о курсе валют
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public Currency getCurrency() throws IllegalArgumentException, IOException {
        logger.info("Получение курса валют");
        Currency currency = new Currency();

        String json = JsonHelper.jsonToString(URL);
        List<String> list = helper.getCurrency(json);

        if (list.size() != 4)
            throw new IllegalArgumentException("API был изменен");

        String usd = list.get(0);
        String usdPrev = list.get(1);
        String eur = list.get(2);
        String eurPrev = list.get(3);

        //округление до 3 знака после запятой
        float usdDiff = Float.parseFloat(usd) - Float.parseFloat(usdPrev);
        usdDiff = (float) Math.round(usdDiff * 1000) / 1000;
        float eurDiff = Float.parseFloat(eur) - Float.parseFloat(eurPrev);
        eurDiff = (float) Math.round(eurDiff * 1000) / 1000;

        currency.setUsd(usd);
        currency.setUsdDiff(usdDiff);

        currency.setEur(eur);
        currency.setEurDiff(eurDiff);

        return currency;
    }

    public static String getURL() {
        return URL;
    }
}
