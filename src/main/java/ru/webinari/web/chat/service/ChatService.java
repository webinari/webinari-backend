package ru.webinari.web.chat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.webinari.web.ApiException;
import ru.webinari.web.chat.model.Message;
import ru.webinari.web.chat.repository.MessageRepository;
import ru.webinari.web.api.room.model.Room;
import ru.webinari.web.api.room.repository.RoomRepository;

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
        message.setRoom(room);
        message = messageRepository.save(message);

        List<Message> messages = room.getMessages();
        messages.add(message);
    }

}
