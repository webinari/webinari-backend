package ru.webinari.web.api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.webinari.web.api.user.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
