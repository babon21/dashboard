package ru.eltex.app.dashboard.currency;

import org.apache.log4j.Logger;
import org.junit.Test;
import ru.eltex.app.dashboard.exception.ApiException;
import ru.eltex.app.dashboard.util.Config;
import ru.eltex.app.dashboard.util.CurrencyHelper;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CBRCurrencyServiceTest {

    final private static CurrencyHelper helper = mock(CurrencyHelper.class);
    private Logger logger = Logger.getLogger(CBRCurrencyServiceTest.class);

    @Test
    public void getFailCurrency() {
        //currency values list
        List<String> list = new ArrayList<>();
        list.add("Special Invalid String For currency");
        list.add("2");
        list.add("5");
        list.add("4.6");

        CurrencyService currencyService = new CBRCurrencyService(helper);
        Currency currency = null;
        try {
            when(helper.getCurrency(Config.CURRENCY_URL)).thenReturn(list);
            currency = currencyService.getCurrency();
        } catch (IOException | ApiException e) {
            logger.error("Failed", e);
        }

        assertNotNull(currency);
    }
}