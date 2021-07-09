package ru.vlsu.practice.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.vlsu.practice.service.dto.NewsDTO;
import ru.vlsu.practice.service.dto.PortalDTO;

@Service
public interface NewsService {

    NewsDTO save(NewsDTO newsDTO) throws Exception;

    Optional<NewsDTO> partialUpdate(NewsDTO newsDTO);

    Page<NewsDTO> findAll(Pageable pageable);

    Optional<NewsDTO> findOne(Long id);

    Page<NewsDTO> findAllByName(String name);

    void delete(Long id);

}
