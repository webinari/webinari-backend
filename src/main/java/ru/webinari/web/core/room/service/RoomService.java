package ru.webinari.web.core.room.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.webinari.web.core.ApiException;
import ru.webinari.web.core.room.controller.RoomController.RoomRequest;
import ru.webinari.web.core.room.model.Room;
import ru.webinari.web.core.room.repository.RoomRepository;
import ru.webinari.web.core.user.model.User;
import ru.webinari.web.core.user.repository.UserRepository;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<Room> getRooms(Long userId) throws ApiException {
        try {
            if (userId != null) {
                return roomRepository.findAllByUser_Id(userId);
            } else {
                return roomRepository.findAll();
            }
        } catch (Exception ex) {
            throw new ApiException(NOT_FOUND, "Cant find rooms", ex);
        }
    }

    public Room getRoom(Long roomId) throws ApiException {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new ApiException(NOT_FOUND, "Room with id " + roomId + " not found"));
    }

    @Transactional
    public Room createRoom(RoomRequest request) throws ApiException {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ApiException(BAD_REQUEST, "User with id " + request.getUserId() + " not found"));

        String roomName = request.getName();
        if (roomRepository.existsByNameAndUser(roomName, user)) {
            try {
                return roomRepository.save(new Room(roomName, user));
            } catch (Exception ex) {
                throw new ApiException(BAD_REQUEST, "Room name already exists");
            }
        }
        throw new ApiException(BAD_REQUEST, "Room with name " + roomName + " already exists");
    }

    public void deleteRoom(Long roomId) throws ApiException {
        try {
            roomRepository.deleteById(roomId);
        } catch (Exception ex) {
            throw new ApiException(BAD_REQUEST, "Cant delete room with id " + roomId, ex);
        }
    }
}
