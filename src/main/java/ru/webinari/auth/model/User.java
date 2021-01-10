package ru.webinari.auth.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "users_id_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "users_id_seq", name = "users_id_sequence", allocationSize = 1)
    private Long Id;
//    @OneToOne
//    @JoinColumn(name = "role_id")
//    private Role role;
    private String username;
    private String password;
    private String email;
//    private UserStatus status;
//    @OneToMany(mappedBy = "owner")
//    private List<Room> rooms;


    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
