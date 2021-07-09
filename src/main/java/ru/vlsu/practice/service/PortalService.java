package ru.vlsu.practice.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vlsu.practice.service.dto.PortalDTO;

public interface PortalService {

    PortalDTO save(PortalDTO portalDTO);

    Optional<PortalDTO> partialUpdate(PortalDTO portalDTO);

    Page<PortalDTO> findAll(Pageable pageable);

    Optional<PortalDTO> findOne(Long id);

    Optional<PortalDTO> findByName(String name);

    void delete(Long id);

}
