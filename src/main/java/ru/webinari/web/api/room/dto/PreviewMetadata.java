package ru.webinari.web.api.room.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import ru.webinari.web.api.room.model.RoomMetadata;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Getter
public class PreviewMetadata {
    @NotNull
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Samara")
    protected final ZonedDateTime startDateTime;
    @NotNull
    protected final boolean started;

    public PreviewMetadata(RoomMetadata metadata) {
        this.startDateTime = metadata.getStartDateTime();
        this.started = metadata.isStarted();
    }
}
