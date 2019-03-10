package ru.eltex.app.dashboard.currency;

import ru.eltex.app.dashboard.exception.UserException;

/**
 * Интерфейс для службы получения курса валют
 * @author darzhain
 */
public interface CurrencyService {
    Currency getCurrency() throws UserException;
}
