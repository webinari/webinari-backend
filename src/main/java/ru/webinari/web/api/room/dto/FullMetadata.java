package ru.webinari.web.api.room.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import ru.webinari.web.api.room.model.RoomMetadata;
import ru.webinari.web.api.room.model.RoomType;

import javax.validation.constraints.NotEmpty;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.*;

@Getter
public class FullMetadata extends PreviewMetadata {
    @NotEmpty
    private final String lecturer;
    @JsonProperty(access = READ_ONLY)
    private final String publicLink;
    @NotEmpty
    private final RoomType type;
    @NotEmpty
    private final String videoId;

    public FullMetadata(RoomMetadata metadata) {
        super(metadata);
        this.lecturer = metadata.getLecturer();
        this.publicLink = metadata.getPublicLink();
        this.type = metadata.getType();
        this.videoId = metadata.getVideoId();
    }
}
