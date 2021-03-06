package ru.webinari.web.api.auth.dto;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import ru.webinari.security.WebinariUserDetails;

import java.security.Principal;

@Getter
public class UserResponse {
    private final Long id;
    private final String username;

    public UserResponse(Principal user) {
        WebinariUserDetails userDetails = (WebinariUserDetails) ((UsernamePasswordAuthenticationToken) user).getPrincipal();
        this.id = userDetails.getUser().getId();
        this.username = userDetails.getUsername();
    }
}
