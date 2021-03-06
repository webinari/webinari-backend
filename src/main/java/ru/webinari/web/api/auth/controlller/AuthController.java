package ru.webinari.web.api.auth.controlller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.webinari.web.ApiException;
import ru.webinari.web.api.auth.dto.UserResponse;
import ru.webinari.web.format.WrappedResponse;

import java.security.Principal;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static ru.webinari.web.format.WrappedResponse.done;

@RestController
@RequestMapping("/api")
public class AuthController {

    @GetMapping("/login")
    public WrappedResponse<UserResponse> user(
            Principal user
    ) throws ApiException {
        if (user == null) {
            throw new ApiException(UNAUTHORIZED, "Unauthorized");
        }
        return done(new UserResponse(user));
    }

}
