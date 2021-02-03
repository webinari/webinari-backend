package ru.webinari.web.core.event.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.webinari.web.core.ApiException;
import ru.webinari.web.core.event.model.Event;
import ru.webinari.web.core.room.model.Room;
import ru.webinari.web.core.room.repository.RoomRepository;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class EventService {

    private final RoomRepository roomRepository;

    public Event eventIsActive(Long userId, String roomPublicId) throws ApiException {
        Room room = roomRepository.findByUser_IdAndPublicId(userId, roomPublicId)
                .orElseThrow(() -> new ApiException(NOT_FOUND, "Room not found"));
        return room.getEvent();
    }
}
