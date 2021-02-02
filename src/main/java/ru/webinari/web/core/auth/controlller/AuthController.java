package ru.webinari.web.core.auth.controlller;

import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.webinari.web.core.ApiException;
import ru.webinari.web.core.WrappedResponse;
import ru.webinari.web.security.WebinariUserDetails;

import java.security.Principal;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static ru.webinari.web.core.ControllerUtils.wrap;

@RestController
@RequestMapping("/api")
public class AuthController {

    @GetMapping("/login")
    public ResponseEntity<WrappedResponse<UserResponse>> user(Principal user) {
        return wrap(() -> {
            if (user == null)
                throw new ApiException(UNAUTHORIZED, "Unauthorized");
            return new UserResponse(user);
        });
    }

    @Getter
    private static class UserResponse {
        private final Long id;
        private final String username;

        public UserResponse(Principal user) {
            WebinariUserDetails userDetails = (WebinariUserDetails) ((UsernamePasswordAuthenticationToken) user).getPrincipal();
            this.id = userDetails.getUser().getId();
            this.username = userDetails.getUsername();
        }
    }

}
