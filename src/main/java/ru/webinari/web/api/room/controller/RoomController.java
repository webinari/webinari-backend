package ru.webinari.web.api.room.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.webinari.web.ApiException;
import ru.webinari.web.api.room.dto.*;
import ru.webinari.web.chat.model.Message;
import ru.webinari.web.format.WrappedResponse;
import ru.webinari.web.api.room.model.Room;
import ru.webinari.web.api.room.model.RoomMetadata;
import ru.webinari.web.api.room.service.RoomService;
import ru.webinari.web.api.user.model.User;
import ru.webinari.security.WebinariUserDetails;

import java.security.Principal;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static ru.webinari.web.format.WrappedResponse.done;

@Validated
@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService service;

    @GetMapping
    public WrappedResponse<List<PreviewRoom>> getRooms(
            Principal principal
    ) throws ApiException {
        User user = getUserFromPrincipal(principal);
        List<Room> rooms = service.getRooms(user.getId());
        return done(rooms.stream().map(PreviewRoom::new).collect(toList()));
    }

    @GetMapping("/{roomId}")
    public WrappedResponse<FullRoom> getRoom(
            @PathVariable("roomId") Long roomId
    ) throws ApiException {
        Room room = service.getRoom(roomId);
        return done(new FullRoom(room));
    }

    @PostMapping
    public WrappedResponse<PreviewRoom> createRoom(
            @RequestBody CreateRoom request, Principal principal
    ) throws ApiException {
        User user = getUserFromPrincipal(principal);
        Room room = service.createRoom(request.getName(), request.getPublicId(), user);
        return done(new PreviewRoom(room));
    }

    @DeleteMapping("/{roomId}")
    public WrappedResponse<Void> deleteRoom(
            @PathVariable("roomId") Long roomId
    ) throws ApiException {
        service.deleteRoom(roomId);
        return done();
    }

    @PostMapping("/{roomId}")
    public WrappedResponse<FullRoom> updateRoom(
            @PathVariable("roomId") Long roomId, @RequestBody UpdateRoom request
    ) throws ApiException {
        Room room = service.updateRoom(roomId, request);
        return done(new FullRoom(room));
    }

    @PutMapping("/{roomId}")
    public WrappedResponse<PreviewMetadata> changeRoomStatus(
            @PathVariable("roomId") Long roomId, @RequestParam("status") boolean status
    ) throws ApiException {
        RoomMetadata roomMetadata = service.changeRoomStatus(roomId, status);
        return done(new PreviewMetadata(roomMetadata));
    }

    @GetMapping("/{userId}/{publicId}")
    public WrappedResponse<PreviewMetadata> getRoomMetadata(
            @PathVariable("userId") Long userId, @PathVariable("publicId") String publicId
    ) throws ApiException {
        RoomMetadata roomMetadata = service.getRoomMetadata(userId, publicId);
        return done(new PreviewMetadata(roomMetadata));
    }

    @GetMapping("/{roomId}/messages")
    public WrappedResponse<List<FullMessage>> getRoomMessages(
            @PathVariable("roomId") Long roomId
    ) {
        List<Message> messages = service.getRoomMessages(roomId);
        return done(messages.stream().map(FullMessage::new).collect(toList()));
    }

    @PostMapping("/{roomId}/messages")
    public WrappedResponse<Void> updateRoomMessages(
            @PathVariable("roomId") Long roomId, @RequestBody List<UpdateMessage> messages
    ) throws ApiException {
        service.updateRoomMessages(roomId, messages);
        return done();
    }

    private User getUserFromPrincipal(Principal principal) {
        return ((WebinariUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
    }

}
