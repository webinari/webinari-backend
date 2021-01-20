package ru.webinari.web.core.user.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.webinari.web.core.ExceptionResponse;
import ru.webinari.web.core.user.UserException;
import ru.webinari.web.core.user.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<ExceptionResponse> regUser(@Valid @RequestBody UserRequest request) {
        try {
            service.regUser(request);
            return ResponseEntity.ok().build();
        } catch (UserException e) {
            return ResponseEntity.badRequest().body(new ExceptionResponse(e.getMessage()));
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class UserRequest {
        @NotEmpty
        private final String username;
        @NotEmpty
        private final String password;
        @Email
        private final String email;
    }

}
