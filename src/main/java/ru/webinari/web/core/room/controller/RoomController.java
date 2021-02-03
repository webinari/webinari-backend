package ru.webinari.web.core.room.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.webinari.web.core.Views.Full;
import ru.webinari.web.core.Views.Preview;
import ru.webinari.web.core.WrappedResponse;
import ru.webinari.web.core.room.model.Room;
import ru.webinari.web.core.room.model.TranslationType;
import ru.webinari.web.core.room.service.RoomService;
import ru.webinari.web.core.user.model.User;
import ru.webinari.web.security.WebinariUserDetails;

import javax.validation.constraints.NotEmpty;
import java.security.Principal;
import java.util.List;

import static ru.webinari.web.core.ControllerUtils.wrap;

@Validated
@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService service;

    @JsonView(Preview.class)
    @GetMapping
    public ResponseEntity<WrappedResponse<List<Room>>> getRooms(Principal principal) {
        User user = getUserFromPrincipal(principal);
        return wrap(() -> service.getRooms(user.getId()));
    }

    @JsonView(Full.class)
    @GetMapping("/{roomId}")
    public ResponseEntity<WrappedResponse<Room>> getRoom(@PathVariable("roomId") Long roomId) {
        return wrap(() -> service.getRoom(roomId));
    }

    @JsonView(Preview.class)
    @PostMapping
    public ResponseEntity<WrappedResponse<Room>> createRoom(@RequestBody RoomRequest request, Principal principal) {
        User user = getUserFromPrincipal(principal);
        return wrap(() -> service.createRoom(request, user));
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<WrappedResponse<Void>> deleteRoom(@PathVariable("roomId") Long roomId) {
        return wrap(() -> {
            service.deleteRoom(roomId);
            return null;
        });
    }

    @JsonView(Preview.class)
    @PostMapping("/{roomId}")
    public ResponseEntity<WrappedResponse<Room>> updateRoom(@PathVariable("roomId") Long roomId, @RequestBody Room request) {
        return wrap(() -> service.updateRoom(roomId, request));
    }

    private User getUserFromPrincipal(Principal principal) {
        return ((WebinariUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
    }

    @Data
    @NoArgsConstructor
    public static class RoomRequest {
        @NotEmpty
        private String name;
        private String publicId;
    }

    @Data
    @NoArgsConstructor
    public static class RoomSettingsRequest {
        @NotEmpty
        private String lecturer;
        @NotEmpty
        private String videoId;
        @NonNull
        private TranslationType type;
    }

}
