package ru.vlsu.practice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ru.vlsu.practice.domain.Event;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the Event entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByName(String name);

    List<Event> findAllByDeleted(Boolean deleted, Pageable pageable);

}
