package ru.webinari.room.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    SimpMessagingTemplate template;

    @MessageMapping("/{chatId}")
    @SendTo("/chat/{chatId}")
    Message handleMessage(@DestinationVariable("chatId") String chatId, Message message) {
        return message;
    }

}
