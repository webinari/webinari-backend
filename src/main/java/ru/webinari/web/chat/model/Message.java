package ru.webinari.web.chat.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.webinari.web.api.room.dto.UpdateMessage;
import ru.webinari.web.api.room.dto.UpdateRoom;
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

    public void update(UpdateMessage message) {
        if (!this.postTime.equals(message.getPostTime()))
            this.postTime = message.getPostTime();
    }

}
