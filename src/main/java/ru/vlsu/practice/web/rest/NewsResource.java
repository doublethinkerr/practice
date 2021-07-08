package ru.vlsu.practice.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.vlsu.practice.repository.NewsRepository;
import ru.vlsu.practice.repository.PortalRepository;
import ru.vlsu.practice.service.NewsService;
import ru.vlsu.practice.service.dto.NewsDTO;
import ru.vlsu.practice.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class NewsResource {

    private final Logger log = LoggerFactory.getLogger(NewsResource.class);

    private static final String ENTITY_NAME = "news";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NewsService newsService;

    private final NewsRepository newsRepository;


    public NewsResource(NewsService newsService, NewsRepository newsRepository, PortalRepository portalRepository) {
        this.newsService = newsService;
        this.newsRepository = newsRepository;
    }

    @PostMapping("/news")
    public ResponseEntity<NewsDTO> createNews(@Valid @RequestBody NewsDTO newsDTO) throws Exception {
        log.debug("REST request to save News : {}", newsDTO);
        if (newsDTO.getId() != null) {
            throw new BadRequestAlertException("A new News cannot already have an ID", ENTITY_NAME, "idexists");
        }
/*        if (portalRepository.findById(newsDTO.getPortalId()).get().getDeleted() == true){
            throw new Exception("A new News cannot connect to deleted portal");
        }*/
        NewsDTO result = newsService.save(newsDTO);
        return ResponseEntity
            .created(new URI("/api/news/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/news/{id}")
    public ResponseEntity<NewsDTO> updateNews(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody NewsDTO newsDTO
        ) throws Exception {
        log.debug("REST request to update News : {}, {}", id, newsDTO);
        if (newsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, newsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!newsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        NewsDTO result = newsService.save(newsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, newsDTO.getId().toString()))
            .body(result);
    }

    @PatchMapping(value = "/news/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<NewsDTO> partialUpdateNews(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody NewsDTO newsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update News partially : {}, {}", id, newsDTO);
        if (newsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, newsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!newsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NewsDTO> result = newsService.partialUpdate(newsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, newsDTO.getId().toString())
        );
    }

    @GetMapping("/news")
    public ResponseEntity<List<NewsDTO>> getAllNews(Pageable pageable) {
        log.debug("REST request to get a page of News");
/*        ArrayList<NewsDTO> list = new ArrayList<>();
        Iterable <NewsDTO> iterable = newsService.findAll(pageable);
        Iterator<NewsDTO> iterator = iterable.iterator();
        while (iterator.hasNext()){
            NewsDTO newsDTO = iterator.next();
            if (newsDTO.getDeleted()==false) list.add(newsDTO);
        }
        Page<NewsDTO> page = new PageImpl<>(list);*/

        Page page = newsService.findAll(pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/news/find")
    public ResponseEntity<NewsDTO> getNewsByName(@RequestParam String name) {
        log.debug("REST request to get News by name : {}", name);
        Optional<NewsDTO> newsDTO = newsService.findByName(name);
        return ResponseUtil.wrapOrNotFound(newsDTO);
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<NewsDTO> getNews(@PathVariable Long id) {
        log.debug("REST request to get News : {}", id);
        Optional<NewsDTO> newsDTO = newsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(newsDTO);
    }

    @DeleteMapping("/news/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        log.debug("REST request to delete News : {}", id);
        newsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }


}
