package ru.webinari.web.core.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.webinari.web.core.room.model.Translation;

public interface TranslationRepository extends JpaRepository<Translation, Long> {
}
