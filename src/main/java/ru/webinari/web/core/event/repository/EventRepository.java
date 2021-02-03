package ru.webinari.web.core.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.webinari.web.core.event.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
