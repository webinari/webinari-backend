package ru.webinari.web.core.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.webinari.web.core.room.model.Room;
import ru.webinari.web.core.user.model.User;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findAllByUser_Id(Long id);

    boolean existsByNameAndUser(String name, User user);
    boolean existsByPublicIdAndUser(String publicId, User user);

}
