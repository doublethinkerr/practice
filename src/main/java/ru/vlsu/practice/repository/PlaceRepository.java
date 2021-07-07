package ru.vlsu.practice.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ru.vlsu.practice.domain.Event;
import ru.vlsu.practice.domain.Place;
import ru.vlsu.practice.service.dto.PlaceDTO;

import java.util.Optional;

/**
 * Spring Data SQL repository for the Place entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    Optional<Place> findByName(String name);
}

