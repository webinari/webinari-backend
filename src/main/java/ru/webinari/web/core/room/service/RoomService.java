package ru.webinari.web.core.room.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.webinari.web.core.ApiException;
import ru.webinari.web.core.room.controller.RoomController.RoomRequest;
import ru.webinari.web.core.room.model.Room;
import ru.webinari.web.core.room.model.RoomMetadata;
import ru.webinari.web.core.event.model.Event;
import ru.webinari.web.core.room.repository.RoomMetadataRepository;
import ru.webinari.web.core.room.repository.RoomRepository;
import ru.webinari.web.core.event.repository.EventRepository;
import ru.webinari.web.core.user.model.User;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMetadataRepository roomMetadataRepository;
    private final EventRepository eventRepository;

    @Transactional
    public List<Room> getRooms(Long userId) throws ApiException {
        try {
            return roomRepository.findAllByUser_Id(userId);
        } catch (Exception ex) {
            throw new ApiException(NOT_FOUND, "Cant find rooms", ex);
        }
    }

    public Room getRoom(Long roomId) throws ApiException {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new ApiException(NOT_FOUND, "Room with id " + roomId + " not found"));
    }

    @Transactional
    public Room createRoom(RoomRequest request, User user) throws ApiException {
        String roomName = request.getName();
        String publicId = request.getPublicId();
        if (roomRepository.existsByPublicIdAndUser(publicId, user))
            throw new ApiException(BAD_REQUEST, "Room with public id " + publicId + " already exists");

        try {
            RoomMetadata roomMetadata = roomMetadataRepository.save(new RoomMetadata(user.getId(), publicId));
            Event event = eventRepository.save(new Event());
            return roomRepository.save(new Room(roomName, publicId, roomMetadata, event, user));
        } catch (Exception ex) {
            throw new ApiException(BAD_REQUEST, "Room name already exists");
        }
    }

    public void deleteRoom(Long roomId) throws ApiException {
        try {
            roomRepository.deleteById(roomId);
        } catch (Exception ex) {
            throw new ApiException(BAD_REQUEST, "Cant delete room with id " + roomId, ex);
        }
    }

    @Transactional
    public Room updateRoom(Long roomId, Room request) throws ApiException {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ApiException(NOT_FOUND, "Room with id " + roomId + " not found"));
        room.update(request);
        return roomRepository.save(room);
    }
}
