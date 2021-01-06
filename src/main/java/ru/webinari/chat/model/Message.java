package ru.webinari.chat.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class Message {

    private String message;
    private Long userId;
    private Instant postTime;

}
