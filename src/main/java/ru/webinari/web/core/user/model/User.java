package ru.webinari.web.core.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.webinari.web.core.room.model.Room;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "users_id_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "users_id_seq", name = "users_id_sequence", allocationSize = 1)
    private Long id;
    private String username;
    private String password;
    private String email;
    @OneToMany(mappedBy = "user")
    private List<Room> rooms;


    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
