package ru.webinari.web.api.room.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.webinari.web.api.room.model.RoomMetadata;
import ru.webinari.web.api.room.model.RoomType;

import javax.validation.constraints.NotEmpty;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
@NoArgsConstructor
public class FullMetadata extends PreviewMetadata {
    @NotEmpty
    private String lecturer;
    @JsonProperty(access = READ_ONLY)
    private String publicLink;
    @NotEmpty
    private RoomType type;
    @NotEmpty
    private String videoId;

    public FullMetadata(RoomMetadata metadata) {
        super(metadata);
        this.lecturer = metadata.getLecturer();
        this.publicLink = metadata.getPublicLink();
        this.type = metadata.getType();
        this.videoId = metadata.getVideoId();
    }
}
