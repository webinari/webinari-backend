package ru.webinari.web.core.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.webinari.web.core.room.model.RoomMetadata;

import java.util.Optional;

public interface RoomMetadataRepository extends JpaRepository<RoomMetadata, Long> {
    Optional<RoomMetadata> findByRoom_Id(Long roomId);
}
