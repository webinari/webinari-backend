package ru.webinari.web.core.event.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.webinari.web.core.Views.Full;
import ru.webinari.web.core.WrappedResponse;
import ru.webinari.web.core.event.model.Event;
import ru.webinari.web.core.event.service.EventService;

import static ru.webinari.web.core.ControllerUtils.wrap;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @JsonView(Full.class)
    @GetMapping("/{userId}/{roomPublicId}")
    public ResponseEntity<WrappedResponse<Event>> eventIsActive(
            @PathVariable("userId") Long userId, @PathVariable("roomPublicId") String roomPublicId
    ) {
        return wrap(() -> eventService.eventIsActive(userId, roomPublicId));
    }

}
