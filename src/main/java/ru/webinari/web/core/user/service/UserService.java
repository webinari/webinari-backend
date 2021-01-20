package ru.webinari.web.core.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.webinari.web.core.user.model.User;
import ru.webinari.web.core.user.repository.UserRepository;
import ru.webinari.web.core.user.UserException;
import ru.webinari.web.core.user.controller.UserController.UserRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void regUser(UserRequest request) throws UserException {
        String rawPassword = request.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        User user = new User(request.getUsername(), encodedPassword, request.getEmail());
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            log.error("New user registration failed", ex);
            throw new UserException("Username already exists");
        }
    }
}