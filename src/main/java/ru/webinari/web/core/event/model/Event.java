package ru.webinari.web.core.event.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.webinari.web.core.Views;
import ru.webinari.web.core.Views.Full;
import ru.webinari.web.core.Views.Preview;
import ru.webinari.web.core.room.model.Room;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(generator = "events_id_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "events_id_seq", name = "events_id_sequence", allocationSize = 1)
    @JsonIgnore
    private Long id;
    @Enumerated
    @JsonView(Full.class)
    private EventType type;
    @JsonView(Full.class)
    private String videoId;
    @JsonView(Full.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Samara")
    private ZonedDateTime startDateTime;


    @OneToOne(mappedBy = "event")
    @JsonIgnore
    private Room room;

    @JsonView(Full.class)
    @JsonGetter
    public boolean isActive() {
        return ZonedDateTime.now(ZoneId.of("Europe/Samara")).isAfter(startDateTime);
    }

    public Event() {
        this.type = EventType.WEBINAR;
    }

    public void update(Event event) {
        if (!this.type.equals(event.getType()))
            this.type = event.getType();

        if (this.videoId == null || !this.videoId.equals(event.getVideoId()))
            this.videoId = event.getVideoId();

        if (this.startDateTime == null || !this.startDateTime.equals(event.getStartDateTime()))
            this.startDateTime = event.getStartDateTime();
    }
}
