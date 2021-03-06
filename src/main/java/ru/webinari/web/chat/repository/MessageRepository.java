package ru.webinari.web.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.webinari.web.chat.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
