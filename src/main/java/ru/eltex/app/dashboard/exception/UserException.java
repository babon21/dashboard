package ru.eltex.app.dashboard.exception;

/**
 * Исключение, вызываемое в случае недоступности удаленного сервиса
 * @author darhzain
 */
public class UserException extends Exception {
    private String message;

    public UserException(String s) {
        message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
