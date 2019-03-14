package ru.eltex.app.dashboard.currency;

import com.jayway.jsonpath.JsonPath;
import org.apache.log4j.Logger;
import ru.eltex.app.dashboard.exception.ApiException;
import ru.eltex.app.dashboard.util.JsonHelper;
import ru.eltex.app.dashboard.util.ValueHelper;

import java.io.IOException;


/**
 * Получение и парсинг курса валют с сайта Центрального Банка России
 *
 * @author darzhain
 */
public class CBRCurrencyService implements CurrencyService {

    /**
     * URL запроса к API сервиса, предоставляющий данные о курсе валют
     * в формате JSON
     **/
    private final String URL_C = "https://www.cbr-xml-daily.ru/daily_json.js";

    private static final Logger logger = Logger.getLogger(CBRCurrencyService.class);

    private static final String ERROR_MESSAGE = "API был изменен, ошибка приведения типов";

    /**
     * Получить данные о курсе валют
     * @return Информацию о курсе валют
     * @throws ApiException
     */
    public Currency getCurrency() throws ApiException, IOException {
        logger.info("Получение курса валют");
        Currency currency = new Currency();

        String jsonCbr = JsonHelper.jsonToString(URL_C);

        String usd = JsonPath.read(jsonCbr, "$.Valute.USD.Value").toString();
        String usdPrev = JsonPath.read(jsonCbr, "$.Valute.USD.Previous").toString();

        String eur = JsonPath.read(jsonCbr, "$.Valute.EUR.Value").toString();
        String eurPrev = JsonPath.read(jsonCbr, "$.Valute.EUR.Previous").toString();


        //проверка полученных данных на соответствие типу float
        if (!ValueHelper.checkFloat(usd) || !ValueHelper.checkFloat(usdPrev)
                || !ValueHelper.checkFloat(eur) || !ValueHelper.checkFloat(eurPrev)) {
            throw new ApiException(ERROR_MESSAGE);
        }

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
}
