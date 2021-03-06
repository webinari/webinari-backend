package ru.webinari.web.chat.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.webinari.web.api.room.model.Room;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column(columnDefinition = "interval")
    private ZonedDateTime postTime;
    private String message;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

}
