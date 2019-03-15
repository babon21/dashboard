package ru.eltex.app.dashboard.currency;

import com.jayway.jsonpath.JsonPath;
import org.apache.log4j.Logger;
import ru.eltex.app.dashboard.exception.ApiException;
import ru.eltex.app.dashboard.util.Config;
import ru.eltex.app.dashboard.util.CurrencyHelper;
import ru.eltex.app.dashboard.util.JsonHelper;
import ru.eltex.app.dashboard.util.ValueHelper;

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
    private static final String URL = Config.CURRENCY_URL;

    private static final Logger logger = Logger.getLogger(CBRCurrencyService.class);

    private static final String ERROR_MESSAGE = "API был изменен, ошибка приведения типов";

    public CBRCurrencyService(CurrencyHelper helper) {
        this.helper = helper;
    }

    /**
     * Получить данные о курсе валют
     * @return Информацию о курсе валют
     * @throws ApiException
     */
    public Currency getCurrency() throws ApiException, IOException {
        logger.info("Получение курса валют");
        Currency currency = new Currency();

        List<String> list = helper.getCurrency(URL);

        if (list.size() != 4)
            throw new ApiException(ERROR_MESSAGE);

        String usd = list.get(0);
        String usdPrev = list.get(1);
        String eur = list.get(2);
        String eurPrev = list.get(3);

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
