package ru.webinari.web.core.user;

import lombok.Getter;

@Getter
public class UserException extends Exception {

    private final String message;

    public UserException(String message) {
        super(message);
        this.message = message;
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}
