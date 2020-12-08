package ru.webinari.model;

import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long Id;
    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;
    private String username;
    private String password;
    private String email;
    @OneToMany(mappedBy = "owner")
    private List<Room> rooms;
}
