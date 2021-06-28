package ru.vlsu.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlsu.practice.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
