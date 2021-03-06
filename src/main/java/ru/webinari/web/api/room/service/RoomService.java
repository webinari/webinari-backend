package ru.webinari.web.api.room.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.webinari.web.ApiException;
import ru.webinari.web.api.room.dto.FullMessage;
import ru.webinari.web.api.room.dto.UpdateMessage;
import ru.webinari.web.api.room.dto.UpdateRoom;
import ru.webinari.web.api.room.model.Room;
import ru.webinari.web.api.room.model.RoomMetadata;
import ru.webinari.web.api.room.repository.RoomMetadataRepository;
import ru.webinari.web.api.room.repository.RoomRepository;
import ru.webinari.web.api.user.model.User;
import ru.webinari.web.chat.model.Message;
import ru.webinari.web.chat.repository.MessageRepository;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMetadataRepository roomMetadataRepository;
    private final MessageRepository messageRepository;

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
    public Room createRoom(String roomName, String publicId, User user) throws ApiException {
        if (roomRepository.existsByPublicIdAndUser(publicId, user))
            throw new ApiException(BAD_REQUEST, "Room with public id " + publicId + " already exists");

        try {
            RoomMetadata roomMetadata = roomMetadataRepository.save(new RoomMetadata(user.getId(), publicId));
            return roomRepository.save(new Room(roomName, publicId, roomMetadata, user));
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
    public Room updateRoom(Long roomId, UpdateRoom request) throws ApiException {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ApiException(NOT_FOUND, "Room with id " + roomId + " not found"));
        room.update(request);
        return room;
    }

    @Transactional
    public RoomMetadata changeRoomStatus(Long roomId, boolean status) throws ApiException {
        RoomMetadata roomMetadata = roomMetadataRepository.findByRoom_Id(roomId)
                .orElseThrow(() -> new ApiException(NOT_FOUND, "RoomMetadata with roomId " + roomId + " not found"));
        roomMetadata.setStarted(status);
        return roomMetadata;
    }

    public RoomMetadata getRoomMetadata(Long userId, String roomPublicId) throws ApiException {
        Room room = roomRepository.findByUser_IdAndPublicId(userId, roomPublicId)
                .orElseThrow(() -> new ApiException(NOT_FOUND, "Room with userId " + userId + " and publicId" + roomPublicId + " not found"));
        return room.getMetadata();
    }

    public List<Message> getRoomMessages(Long roomId) {
        return messageRepository.findAllByRoom_id(roomId);
    }

    @Transactional
    public void updateRoomMessages(Long roomId, List<UpdateMessage> messages) throws ApiException {
        for (UpdateMessage newMessage : messages) {
            Message message = messageRepository.findByIdAndRoom_Id(newMessage.getId(), roomId)
                    .orElseThrow(() -> new ApiException(NOT_FOUND, "Message with roomId " + roomId + " not found"));
            message.update(newMessage);
        }
    }
}
