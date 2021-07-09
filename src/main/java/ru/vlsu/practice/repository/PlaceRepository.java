package ru.vlsu.practice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ru.vlsu.practice.domain.Event;
import ru.vlsu.practice.domain.Place;
import ru.vlsu.practice.service.dto.PlaceDTO;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the Place entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> findAllByName(String name);

    List<Place> findAllByDeleted(Boolean deleted, Pageable pageable);

}

