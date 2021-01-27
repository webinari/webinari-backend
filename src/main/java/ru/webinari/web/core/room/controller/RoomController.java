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

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService service;

    @GetMapping
    public ResponseEntity<WrappedResponse<List<Room>>> getRooms(Principal principal) {
        User user = getUserFromPrincipal(principal);
        try {
            List<Room> rooms = service.getRooms(user.getId());
            return ResponseEntity.ok(WrappedResponse.done(rooms));
        } catch (ApiException ex) {
            return ResponseEntity.status(ex.getStatus()).body(WrappedResponse.fail(ex.getMessage()));
        }
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<WrappedResponse<Room>> getRoom(@PathVariable("roomId") Long roomId) {
        try {
            Room room = service.getRoom(roomId);
            return ResponseEntity.ok(WrappedResponse.done(room));
        } catch (ApiException ex) {
            return ResponseEntity.status(ex.getStatus()).body(WrappedResponse.fail(ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<WrappedResponse<Room>> createRoom(@Valid @RequestBody RoomRequest request, Principal principal) {
        User user = getUserFromPrincipal(principal);
        try {
            Room room = service.createRoom(request, user);
            return ResponseEntity.ok(WrappedResponse.done(room));
        } catch (ApiException ex) {
            return ResponseEntity.status(ex.getStatus()).body(WrappedResponse.fail(ex.getMessage()));
        }
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<WrappedResponse<Void>> deleteRoom(@PathVariable("roomId") Long roomId) {
        try {
            service.deleteRoom(roomId);
            return ResponseEntity.ok(WrappedResponse.done());
        } catch (ApiException ex) {
            return ResponseEntity.status(ex.getStatus()).body(WrappedResponse.fail(ex.getMessage()));
        }
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
