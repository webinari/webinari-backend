package ru.webinari.web.core.room.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.webinari.web.core.Views.Full;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "rooms_metadata")
public class RoomMetadata {

    @Id
    @GeneratedValue(generator = "rooms_metadata_id_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "rooms_metadata_id_seq", name = "rooms_metadata_id_sequence", allocationSize = 1)
    @JsonIgnore
    private Long id;
    @JsonView(Full.class)
    private String lecturer;
    @JsonView(Full.class)
    private String publicLink;


    @OneToOne(mappedBy = "metadata")
    @JsonIgnore
    private Room room;

    public RoomMetadata(Long userId, String publicId) {
        this.publicLink = "/event/" + userId + "/" + publicId;
    }

    public void update(RoomMetadata metadata) {
        if (this.lecturer == null || !this.lecturer.equals(metadata.getLecturer()))
            this.lecturer = metadata.getLecturer();
    }
}
