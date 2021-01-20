package ru.webinari.chat.controlleer;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.webinari.chat.model.Message;

@Controller
public class ChatController {

    @MessageMapping("/{chatId}")
    @SendTo("/api/chat/{chatId}")
    Message handleMessage(@DestinationVariable("chatId") String chatId, Message message) {
        return message;
    }

}
