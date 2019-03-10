package ru.eltex.app.dashboard.counter;

import ru.eltex.app.dashboard.exception.UserException;

/**
 * Интерфейс для службы по учету количества посетителей
 * @author darzhain
 */
public interface CountService {
    int getCount() throws UserException;
}
