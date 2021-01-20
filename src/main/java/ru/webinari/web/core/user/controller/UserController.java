package ru.webinari.web.core.user.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.webinari.web.core.WrappedResponse;
import ru.webinari.web.core.ApiException;
import ru.webinari.web.core.user.model.User;
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
    public ResponseEntity<WrappedResponse<User>> regUser(@Valid @RequestBody UserRequest request) {
        try {
            service.regUser(request);
            return ResponseEntity.ok().build();
        } catch (ApiException e) {
            return ResponseEntity.badRequest().body(WrappedResponse.fail(e.getMessage()));
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
