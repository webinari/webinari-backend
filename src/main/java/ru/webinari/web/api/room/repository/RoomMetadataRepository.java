package ru.webinari.web.api.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.webinari.web.api.room.model.RoomMetadata;

import java.util.Optional;

public interface RoomMetadataRepository extends JpaRepository<RoomMetadata, Long> {
    Optional<RoomMetadata> findByRoom_Id(Long roomId);
}
