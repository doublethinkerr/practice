package ru.vlsu.practice.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vlsu.practice.service.dto.TodoDTO;

/**
 * Service Interface for managing {@link ru.vlsu.practice.domain.Todo}.
 */
public interface TodoService {
    /**
     * Save a todo.
     *
     * @param todoDTO the entity to save.
     * @return the persisted entity.
     */
    TodoDTO save(TodoDTO todoDTO);

    /**
     * Partially updates a todo.
     *
     * @param todoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TodoDTO> partialUpdate(TodoDTO todoDTO);

    /**
     * Get all the todos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TodoDTO> findAll(Pageable pageable);

    /**
     * Get the "id" todo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TodoDTO> findOne(Long id);

    /**
     * Delete the "id" todo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
