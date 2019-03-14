package ru.eltex.app.dashboard.currency;


import ru.eltex.app.dashboard.exception.ApiException;

import java.io.IOException;

/**
 * Интерфейс для службы получения курса валют
 * @author darzhain
 */
public interface CurrencyService {
    Currency getCurrency() throws IOException, ApiException;
}
