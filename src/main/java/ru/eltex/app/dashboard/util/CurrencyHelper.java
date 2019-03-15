package ru.eltex.app.dashboard.util;

import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyHelper {

    public List<String> getCurrency(String url) throws IOException {
        String json = JsonHelper.jsonToString(url);

        List<String> list = new ArrayList<>();

        String usd = JsonPath.read(json, "$.Valute.USD.Value").toString();
        String usdPrev = JsonPath.read(json, "$.Valute.USD.Previous").toString();
        String eur = JsonPath.read(json, "$.Valute.EUR.Value").toString();
        String eurPrev = JsonPath.read(json, "$.Valute.EUR.Previous").toString();

        list.add(usd);
        list.add(usdPrev);
        list.add(eur);
        list.add(eurPrev);

        return list;
    }
}
