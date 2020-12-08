package ru.webinari.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rooms")
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(generator = "rooms_id_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "rooms_id_seq", name = "rooms_id_sequence")
    private Long Id;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    private String name;

}
