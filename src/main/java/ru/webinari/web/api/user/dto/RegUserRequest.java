package ru.webinari.web.api.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@RequiredArgsConstructor
public class RegUserRequest {
    @NotEmpty
    private final String username;
    @NotEmpty
    private final String password;
    @Email
    private final String email;
}
