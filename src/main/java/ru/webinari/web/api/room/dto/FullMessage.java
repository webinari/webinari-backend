package ru.webinari.web.api.room.dto;

import lombok.Getter;
import ru.webinari.web.chat.model.Message;

import java.time.ZonedDateTime;

@Getter
public class FullMessage {
    private final Long id;
    private final String username;
    private final ZonedDateTime postTime;
    private final String message;

    public FullMessage(Message message) {
        this.id = message.getId();
        this.username = message.getUsername();
        this.postTime = message.getPostTime();
        this.message = message.getMessage();
    }

}
