package ru.eltex.app.dashboard.counter;

import ru.eltex.app.dashboard.exception.UserException;

public interface CountService {
    int getCount() throws UserException;
}
