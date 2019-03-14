package ru.eltex.app.dashboard.exception;

/**
 * Исключение, вызываемое в случае недоступности удаленного сервиса
 * @author darhzain
 */
public class ApiException extends Exception {
    private String message;

    public ApiException(String s) {
        message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
