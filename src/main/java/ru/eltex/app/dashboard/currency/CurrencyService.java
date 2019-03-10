package ru.eltex.app.dashboard.currency;

import ru.eltex.app.dashboard.exception.UserException;

public interface CurrencyService {
    Currency getCurrency() throws UserException;
}
