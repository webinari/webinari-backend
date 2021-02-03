package ru.webinari.web.core.room.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.webinari.web.core.Views.Full;
import ru.webinari.web.core.Views.Preview;
import ru.webinari.web.core.event.model.Event;
import ru.webinari.web.core.user.model.User;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(generator = "rooms_id_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "rooms_id_seq", name = "rooms_id_sequence", allocationSize = 1)
    @JsonView(Preview.class)
    private Long id;
    @JsonView(Preview.class)
    private String name;
    @JsonView(Preview.class)
    private String publicId;
    @OneToOne
    @JoinColumn(name = "metadata_id")
    @JsonView(Full.class)
    private RoomMetadata metadata;
    @OneToOne
    @JoinColumn(name = "event_id")
    @JsonView(Full.class)
    private Event event;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Room(String name, String publicId, RoomMetadata metadata, Event event, User user) {
        this.name = name;
        this.publicId = publicId;
        this.metadata = metadata;
        this.event = event;
        this.user = user;
    }

    public void update(Room room) {
        if (!this.name.equals(room.getName()))
            this.name = room.getName();

        this.metadata.update(room.getMetadata());
        this.event.update(room.getEvent());
    }
}
