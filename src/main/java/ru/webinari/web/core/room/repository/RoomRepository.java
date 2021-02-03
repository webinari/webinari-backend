package ru.webinari.web.core.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.webinari.web.core.room.model.Room;
import ru.webinari.web.core.user.model.User;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findAllByUser_Id(Long id);
    Optional<Room> findByUser_IdAndPublicId(Long id, String publicId);

    boolean existsByPublicIdAndUser(String publicId, User user);

}
