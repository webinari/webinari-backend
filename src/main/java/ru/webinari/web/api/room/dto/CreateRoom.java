package ru.webinari.web.api.room.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CreateRoom {
    @NotEmpty
    private String name;
    private String publicId;
}
