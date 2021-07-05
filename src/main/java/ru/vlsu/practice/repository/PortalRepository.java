package ru.vlsu.practice.repository;


import liquibase.pro.packaged.T;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlsu.practice.domain.Portal;
import ru.vlsu.practice.service.dto.PortalDTO;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortalRepository extends JpaRepository<Portal, Long> {

    Optional<Portal> findByName(String name);
}
