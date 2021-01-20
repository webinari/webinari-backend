package ru.webinari.web.core.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.webinari.web.core.user.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
