package ru.vlsu.practice.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.vlsu.practice.repository.PortalRepository;
import ru.vlsu.practice.service.PortalService;

import ru.vlsu.practice.service.dto.PortalDTO;
import ru.vlsu.practice.service.dto.TodoDTO;
import ru.vlsu.practice.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PortalResource {

    private final Logger log = LoggerFactory.getLogger(TodoResource.class);

    private static final String ENTITY_NAME = "todo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PortalService portalService;

    private final PortalRepository portalRepository;

    public PortalResource(PortalService portalService, PortalRepository portalRepository) {
        this.portalService = portalService;
        this.portalRepository = portalRepository;
    }

    @PostMapping("/portals")
    public ResponseEntity<PortalDTO> createPortal(@Valid @RequestBody PortalDTO portalDTO) throws URISyntaxException {
        log.debug("REST request to save Portal : {}", portalDTO);
        if (portalDTO.getId() != null) {
            throw new BadRequestAlertException("A new portal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PortalDTO result = portalService.save(portalDTO);
        return ResponseEntity
            .created(new URI("/api/portals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/portals/{id}")
    public ResponseEntity<PortalDTO> updatePortal(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PortalDTO portalDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Portal : {}, {}", id, portalDTO);
        if (portalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, portalDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!portalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PortalDTO result = portalService.save(portalDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, portalDTO.getId().toString()))
            .body(result);
    }

    @PatchMapping(value = "/portals/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<PortalDTO> partialUpdatePortal(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PortalDTO portalDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Portal partially : {}, {}", id, portalDTO);
        if (portalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, portalDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!portalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PortalDTO> result = portalService.partialUpdate(portalDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, portalDTO.getId().toString())
        );
    }

    @GetMapping("/portals")
    public ResponseEntity<List<PortalDTO>> getAllPortals(Pageable pageable) {
        log.debug("REST request to get a page of Portals");
        Page<PortalDTO> page = portalService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/portals/{id}")
    public ResponseEntity<PortalDTO> getPortal(@PathVariable Long id) {
        log.debug("REST request to get Portal : {}", id);
        Optional<PortalDTO> portalDTO = portalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(portalDTO);
    }

    @DeleteMapping("/portals/{id}")
    public ResponseEntity<Void> deletePortal(@PathVariable Long id) {
        log.debug("REST request to delete Portal : {}", id);
        portalService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

}
