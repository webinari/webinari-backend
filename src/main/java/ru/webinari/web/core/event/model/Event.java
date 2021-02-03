package ru.webinari.web.core.event.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.webinari.web.core.Views.Full;
import ru.webinari.web.core.room.model.Room;

import javax.persistence.*;
import java.time.Instant;

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
    private Instant startDateTime;


    @OneToOne(mappedBy = "event")
    @JsonIgnore
    private Room room;


    public Event() {
        this.type = EventType.WEBINAR;
    }

    public void update(Event event) {
        if (!this.type.equals(event.getType()))
            this.type = event.getType();

        if (this.videoId == null || !this.videoId.equals(event.getVideoId()))
            this.videoId = event.getVideoId();
    }
}
