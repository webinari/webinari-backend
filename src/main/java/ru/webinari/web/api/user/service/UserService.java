package ru.webinari.web.api.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.webinari.web.api.user.model.User;
import ru.webinari.web.api.user.repository.UserRepository;
import ru.webinari.web.ApiException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void regUser(String password, String username, String email) throws ApiException {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, encodedPassword, email);
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            log.error("New user registration failed", ex);
            throw new ApiException(BAD_REQUEST, "Username already exists");
        }
    }
}
