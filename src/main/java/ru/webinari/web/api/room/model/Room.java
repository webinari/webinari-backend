package ru.webinari.web.api.room.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.webinari.web.api.room.dto.UpdateRoom;
import ru.webinari.web.chat.model.Message;
import ru.webinari.web.api.user.model.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String publicId;
    @OneToOne
    @JoinColumn(name = "metadata_id")
    private RoomMetadata metadata;

    @OneToMany(mappedBy = "room")
    @OrderBy("postTime desc")
    private List<Message> messages = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Room(String name, String publicId, RoomMetadata metadata, User user) {
        this.name = name;
        this.publicId = publicId;
        this.metadata = metadata;
        this.user = user;
    }

    public void update(UpdateRoom room) {
        if (!this.name.equals(room.getName()))
            this.name = room.getName();

        this.metadata.update(room.getMetadata());
    }
}
