package ru.webinari.configuration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.webinari.web.ApiException;
import ru.webinari.web.format.WrappedResponse;

import static ru.webinari.web.format.WrappedResponse.fail;

@RestControllerAdvice
public class WebinariControllerAdvice {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<WrappedResponse<?>> handleApiException(ApiException ex) {
        return ResponseEntity.badRequest().body(fail(ex.getMessage()));
    }

}
