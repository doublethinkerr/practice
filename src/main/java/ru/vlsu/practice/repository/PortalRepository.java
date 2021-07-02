package ru.vlsu.practice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlsu.practice.domain.Portal;

@Repository
public interface PortalRepository extends JpaRepository<Portal, Long> {
}
