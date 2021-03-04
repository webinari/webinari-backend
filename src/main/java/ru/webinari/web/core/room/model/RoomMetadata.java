package ru.webinari.web.core.room.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.webinari.web.core.Views;
import ru.webinari.web.core.Views.Full;
import ru.webinari.web.core.Views.Preview;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "rooms_metadata")
public class RoomMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @JsonView(Full.class)
    private String lecturer;
    @JsonView(Full.class)
    private String publicLink;
    @Enumerated
    @JsonView(Full.class)
    private RoomType type = RoomType.WEBINAR;
    @JsonView(Full.class)
    private String videoId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Samara")
    @JsonView(Preview.class)
    private ZonedDateTime startDateTime;
    @JsonView(Preview.class)
    private boolean started = false;

    @OneToOne(mappedBy = "metadata")
    @JsonIgnore
    private Room room;

    public RoomMetadata(Long userId, String publicId) {
        this.publicLink = "/room/" + userId + "/" + publicId;
    }

    public void update(RoomMetadata metadata) {
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
