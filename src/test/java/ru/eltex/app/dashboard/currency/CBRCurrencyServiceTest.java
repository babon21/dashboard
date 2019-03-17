package ru.eltex.app.dashboard.currency;

import org.apache.log4j.Logger;
import org.junit.Test;
import ru.eltex.app.dashboard.util.CurrencyHelper;
import ru.eltex.app.dashboard.util.JsonHelper;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CBRCurrencyServiceTest {

    //final private static CurrencyHelper helper = mock(CurrencyHelper.class);
    private Logger logger = Logger.getLogger(CBRCurrencyServiceTest.class);

    @Test
    public void getFailCurrency() {
        //currency values list
        List<String> list = new ArrayList<>();
        list.add("Special Invalid String For currency");
        list.add("2");
        list.add("5");
        list.add("4.6");

        CurrencyHelper helper = mock(CurrencyHelper.class);
        CurrencyService currencyService = new CBRCurrencyService(helper);
        Currency currency = null;
        try {
            String json = JsonHelper.jsonToString(CBRCurrencyService.getURL());

            when(helper.getCurrency(json)).thenReturn(list);
            currency = currencyService.getCurrency();
        } catch (IOException | IllegalArgumentException e) {
            logger.error("Failed", e);
        }

        assertNull(currency);
    }

    @Test
    public void getCurrency() {
        //currency values list
        List<String> list = new ArrayList<>();
        list.add("22");
        list.add("2");
        list.add("5");
        list.add("4.6");

        CurrencyHelper helper = mock(CurrencyHelper.class);
        CurrencyService currencyService = new CBRCurrencyService(helper);
        Currency currency = null;
        try {
            String json = JsonHelper.jsonToString(CBRCurrencyService.getURL());

            when(helper.getCurrency(json)).thenReturn(list);
            currency = currencyService.getCurrency();
        } catch (IOException | IllegalArgumentException e) {
            logger.error("Failed", e);
        }

        System.out.println(currency);
        assertNotNull(currency);
    }

}