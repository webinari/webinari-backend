package ru.webinari.web.core.room.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.webinari.web.core.Views.Full;

import javax.persistence.*;

@Data
@Entity
@Table(name = "translations")
public class Translation {

    @Id
    @GeneratedValue(generator = "translations_id_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "translations_id_seq", name = "translations_id_sequence", allocationSize = 1)
    @JsonIgnore
    private Long id;
    @Enumerated
    @JsonView(Full.class)
    private TranslationType type;
    @JsonView(Full.class)
    private String videoId;


    @OneToOne(mappedBy = "translation")
    @JsonIgnore
    private Room room;


    public Translation() {
        this.type = TranslationType.WEBINAR;
    }

    public void update(Translation translation) {
        if (!this.type.equals(translation.getType()))
            this.type = translation.getType();

        if (this.videoId == null || !this.videoId.equals(translation.getVideoId()))
            this.videoId = translation.getVideoId();
    }
}
