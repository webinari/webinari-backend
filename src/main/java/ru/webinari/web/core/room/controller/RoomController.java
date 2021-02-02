package ru.webinari.web.core.room.controller;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ru.webinari.web.core.ApiException;
import ru.webinari.web.core.WrappedResponse;
import ru.webinari.web.core.room.model.Room;
import ru.webinari.web.core.room.service.RoomService;
import ru.webinari.web.core.user.model.User;
import ru.webinari.web.security.WebinariUserDetails;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.security.Principal;
import java.util.List;

import static ru.webinari.web.core.ControllerUtils.wrap;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService service;

    @GetMapping
    public ResponseEntity<WrappedResponse<List<Room>>> getRooms(Principal principal) {
        User user = getUserFromPrincipal(principal);
        return wrap(() -> service.getRooms(user.getId()));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<WrappedResponse<Room>> getRoom(@PathVariable("roomId") Long roomId) {
        return wrap(() -> service.getRoom(roomId));
    }

    @PostMapping
    public ResponseEntity<WrappedResponse<Room>> createRoom(@Valid @RequestBody RoomRequest request, Principal principal) {
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

}
