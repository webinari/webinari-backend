package ru.webinari.web.api.room.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.webinari.web.api.room.model.Room;

@Data
@NoArgsConstructor
public class FullRoom extends PreviewRoom {
    private FullMetadata metadata;

    public FullRoom(Room room) {
        super(room);
        this.metadata = new FullMetadata(room.getMetadata());
    }

}
