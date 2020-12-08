package ru.webinari.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(generator = "roles_id_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "roles_id_seq", name = "roles_id_sequence", allocationSize = 1)
    private Long Id;
    private String name;

}
