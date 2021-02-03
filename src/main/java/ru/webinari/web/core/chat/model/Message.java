package ru.webinari.web.core.chat.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.webinari.web.core.event.model.Event;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(generator = "messages_id_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "messages_id_seq", name = "messages_id_sequence", allocationSize = 1)
    private Long id;
    private String username;
    @Column(columnDefinition = "interval")
    private ZonedDateTime postTime;
    private String message;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

}
