package ru.webinari.web.core.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.webinari.web.core.room.model.RoomMetadata;

public interface RoomMetadataRepository extends JpaRepository<RoomMetadata, Long> {
}
