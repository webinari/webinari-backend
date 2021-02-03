package ru.webinari.web.core.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.webinari.web.core.chat.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
