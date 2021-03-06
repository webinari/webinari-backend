package ru.webinari.web.api.room.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class UpdateMessage {
    private final Long id;
    private final ZonedDateTime postTime;
    private final String message;
}
