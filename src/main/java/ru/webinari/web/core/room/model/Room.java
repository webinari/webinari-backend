package ru.webinari.web.core.room.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.webinari.web.core.user.model.User;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rooms")
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(generator = "rooms_id_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "rooms_id_seq", name = "rooms_id_sequence", allocationSize = 1)
    private Long id;
    private String name;
    private String publicId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Room(String name, String publicId, User user) {
        this.name = name;
        this.publicId = publicId;
        this.user = user;
    }
}
