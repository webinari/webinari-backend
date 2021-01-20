package ru.webinari.web.core.room.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.webinari.web.core.ApiException;
import ru.webinari.web.core.WrappedResponse;
import ru.webinari.web.core.room.model.Room;
import ru.webinari.web.core.room.service.RoomService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService service;

    @GetMapping
    public ResponseEntity<WrappedResponse<List<Room>>> getRooms(@RequestParam(name = "userId", required = false) Long userId) {
        try {
            List<Room> rooms = service.getRooms(userId);
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
    public ResponseEntity<WrappedResponse<Room>> createRoom(@Valid @RequestBody RoomRequest request) {
        try {
            Room room = service.createRoom(request);
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

    @Getter
    @RequiredArgsConstructor
    public static class RoomRequest {
        private final Long userId;
        private final String name;
    }

}
