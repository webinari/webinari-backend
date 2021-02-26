package ru.webinari.web.core.chat.controlleer;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.webinari.web.core.ApiException;
import ru.webinari.web.core.chat.model.Message;
import ru.webinari.web.core.chat.service.ChatService;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/{userId}/{eventKey}")
    @SendTo("/api/chat/{userId}/{eventKey}")
    public Message handleMessage(
            @DestinationVariable("userId") Long userId, @DestinationVariable("eventKey") String eventKey, Message message
    ) throws ApiException {
        chatService.saveMessage(userId, eventKey, message);
        return message;
    }

}
