package ru.webinari.web.api.room.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.webinari.web.api.room.model.Room;

@Data
@NoArgsConstructor
public class PreviewRoom {
    protected Long id;
    protected String name;
    protected String publicId;

    public PreviewRoom(Room room) {
        this.id = room.getId();
        this.name = room.getName();
        this.publicId = room.getPublicId();
    }
}
