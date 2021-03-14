package ru.webinari.web.api.room.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.webinari.web.api.room.model.RoomMetadata;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Data
@NoArgsConstructor
public class PreviewMetadata {
    @NotNull
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Samara")
    protected ZonedDateTime startDateTime;
    @NotNull
    protected boolean started;

    public PreviewMetadata(RoomMetadata metadata) {
        this.startDateTime = metadata.getStartDateTime();
        this.started = metadata.isStarted();
    }
}
