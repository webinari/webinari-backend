package ru.webinari.web.core;

import lombok.Getter;

@Getter
public class ApiException extends Exception {

    private final String message;

    public ApiException(String message) {
        super(message);
        this.message = message;
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}
