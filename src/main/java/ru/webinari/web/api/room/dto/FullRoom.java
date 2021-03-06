package ru.webinari.web.api.room.dto;

import lombok.Getter;
import ru.webinari.web.api.room.model.Room;


@Getter
public class FullRoom extends PreviewRoom {
    private final FullMetadata metadata;

    public FullRoom(Room room) {
        super(room);
        this.metadata = new FullMetadata(room.getMetadata());
    }

}
