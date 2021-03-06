package ru.webinari.web.api.room.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.webinari.web.api.room.model.RoomType;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class RoomSettings {
    @NotEmpty
    private String lecturer;
    @NotEmpty
    private String videoId;
    @NonNull
    private RoomType type;
}
