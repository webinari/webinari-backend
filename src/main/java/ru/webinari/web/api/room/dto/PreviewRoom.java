package ru.webinari.web.api.room.dto;

import lombok.Getter;
import ru.webinari.web.api.room.model.Room;

@Getter
public class PreviewRoom {
    protected final Long id;
    protected final String name;
    protected final String publicId;

    public PreviewRoom(Room room) {
        this.id = room.getId();
        this.name = room.getName();
        this.publicId = room.getPublicId();
    }
}
