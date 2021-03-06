package ru.webinari.web.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.webinari.web.chat.model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByRoom_id(Long roomId);
    Optional<Message> findByIdAndRoom_Id(Long id, Long roomId);
}
