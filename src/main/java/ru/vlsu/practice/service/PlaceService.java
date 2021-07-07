package ru.vlsu.practice.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vlsu.practice.service.dto.PlaceDTO;

/**
 * Service Interface for managing {@link ru.vlsu.practice.domain.Place}.
 */
public interface PlaceService {
    /**
     * Save a place.
     *
     * @param placeDTO the entity to save.
     * @return the persisted entity.
     */
    PlaceDTO save(PlaceDTO placeDTO);

    /**
     * Partially updates a place.
     *
     * @param placeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PlaceDTO> partialUpdate(PlaceDTO placeDTO);

    /**
     * Get all the places.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PlaceDTO> findAll(Pageable pageable);

    /**
     * Get the "id" place.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PlaceDTO> findOne(Long id);

    /**
     * Delete the "id" place.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get the "name" place.
     *
     * @param name the id of the entity.
     * @return the entity.
     */
    Optional<PlaceDTO> findByName(String name);
}
