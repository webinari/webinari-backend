package ru.webinari.web.api.room.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.webinari.web.api.room.dto.FullMetadata;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "rooms_metadata")
public class RoomMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lecturer;
    private String publicLink;
    @Enumerated
    private RoomType type = RoomType.WEBINAR;
    private String videoId;
    private ZonedDateTime startDateTime;
    private boolean started = false;

    @OneToOne(mappedBy = "metadata")
    private Room room;

    public RoomMetadata(Long userId, String publicId) {
        this.publicLink = "/room/" + userId + "/" + publicId;
    }

    public void update(FullMetadata metadata) {
        if (this.lecturer == null || !this.lecturer.equals(metadata.getLecturer()))
            this.lecturer = metadata.getLecturer();

        if (!this.type.equals(metadata.getType()))
            this.type = metadata.getType();

        if (this.videoId == null || !this.videoId.equals(metadata.getVideoId()))
            this.videoId = metadata.getVideoId();

        if (this.startDateTime == null || !this.startDateTime.equals(metadata.getStartDateTime()))
            this.startDateTime = metadata.getStartDateTime();
    }
}
