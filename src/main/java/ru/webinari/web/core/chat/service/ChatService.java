package ru.webinari.web.core.chat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.webinari.web.core.ApiException;
import ru.webinari.web.core.chat.model.Message;
import ru.webinari.web.core.chat.repository.MessageRepository;
import ru.webinari.web.core.room.model.Room;
import ru.webinari.web.core.room.repository.RoomRepository;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final RoomRepository roomRepository;
    private final MessageRepository messageRepository;

    @Transactional
    public void saveMessage(Long userId, String eventKey, Message message) throws ApiException {
        Room room = roomRepository.findByUser_IdAndPublicId(userId, eventKey)
                .orElseThrow(() -> new ApiException(NOT_FOUND, "Room not found"));
        List<Message> messages = room.getMessages();
        message = messageRepository.save(message);
        messages.add(message);
    }

}
