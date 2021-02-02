package ru.webinari.web.core;

import org.springframework.http.ResponseEntity;

public class ControllerUtils {

    public static <R> ResponseEntity<WrappedResponse<R>> wrap(ApiFunction<R> func) {
        try {
            R result = func.exec();
            return ResponseEntity.ok(WrappedResponse.done(result));
        } catch (ApiException ex) {
            return ResponseEntity.badRequest().body(WrappedResponse.fail(ex.getMessage()));
        }
    }

}
