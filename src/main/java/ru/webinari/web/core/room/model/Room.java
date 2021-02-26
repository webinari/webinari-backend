package ru.webinari.web.core.room.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.webinari.web.core.Views.Preview;
import ru.webinari.web.core.chat.model.Message;
import ru.webinari.web.core.user.model.User;

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
    @JsonView(Preview.class)
    private Long id;
    @JsonView(Preview.class)
    private String name;
    @JsonView(Preview.class)
    private String publicId;
    @OneToOne
    @JoinColumn(name = "metadata_id")
    private RoomMetadata metadata;

    @OneToMany(mappedBy = "room")
    @OrderBy("postTime desc")
    private List<Message> messages = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Room(String name, String publicId, RoomMetadata metadata, User user) {
        this.name = name;
        this.publicId = publicId;
        this.metadata = metadata;
        this.user = user;
    }

    public void update(Room room) {
        if (!this.name.equals(room.getName()))
            this.name = room.getName();

        this.metadata.update(room.getMetadata());
    }
}
