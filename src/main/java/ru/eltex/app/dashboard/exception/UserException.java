package ru.mail.dmitrii.exception;

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
