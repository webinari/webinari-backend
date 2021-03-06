package ru.webinari.web.api.room.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.webinari.web.api.room.model.RoomMetadata;
import ru.webinari.web.api.user.model.User;
import ru.webinari.web.chat.model.Message;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UpdateRoom {
    @NotNull
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String publicId;
    private FullMetadata metadata;
}
