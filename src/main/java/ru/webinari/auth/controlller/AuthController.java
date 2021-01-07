package ru.webinari.auth.controlller;

import lombok.Getter;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.webinari.security.WebinariUserDetails;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class AuthController {

    @GetMapping("/login")
    public ResponseEntity<UserResponse> user(Principal user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(new UserResponse(user));

    }

    @Getter
    private static class UserResponse {
        private final Long id;
        private final String username;

        public UserResponse(Principal user) {
            WebinariUserDetails userDetails = (WebinariUserDetails) ((UsernamePasswordAuthenticationToken) user).getPrincipal();
            this.id = userDetails.getUserId();
            this.username = userDetails.getUsername();
        }
    }

}
