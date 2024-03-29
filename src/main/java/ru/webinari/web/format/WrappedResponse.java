package ru.webinari.web.format;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class WrappedResponse<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;
    private final boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Error error;

    public static <T> WrappedResponse<T> done(T data) {
        return new WrappedResponse<>(data, true, null);
    }

    public static <T> WrappedResponse<T> done() {
        return new WrappedResponse<>(null, true, null);
    }

    public static <T> WrappedResponse<T> fail(String message) {
        return new WrappedResponse<>(null, false, new Error(message));
    }

    @Getter
    @RequiredArgsConstructor
    private static class Error {
        private final String message;
    }
}
