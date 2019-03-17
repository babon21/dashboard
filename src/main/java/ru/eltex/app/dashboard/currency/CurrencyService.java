package ru.eltex.app.dashboard.currency;


import java.io.IOException;

/**
 * Интерфейс для службы получения курса валют
 * @author darzhain
 */
public interface CurrencyService {
    Currency getCurrency() throws IOException, IllegalArgumentException;
}
