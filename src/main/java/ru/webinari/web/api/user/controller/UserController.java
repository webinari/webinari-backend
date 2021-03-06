package ru.webinari.web.api.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.webinari.web.ApiException;
import ru.webinari.web.api.user.dto.RegUserRequest;
import ru.webinari.web.api.user.service.UserService;
import ru.webinari.web.format.WrappedResponse;

import javax.validation.Valid;

import static ru.webinari.web.format.WrappedResponse.done;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public WrappedResponse<Void> regUser(
            @Valid @RequestBody RegUserRequest request
    ) throws ApiException {
        service.regUser(request.getPassword(), request.getUsername(), request.getEmail());
        return done();
    }

}
