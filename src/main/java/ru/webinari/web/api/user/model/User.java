package ru.webinari.web.api.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.webinari.web.api.room.model.Room;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
