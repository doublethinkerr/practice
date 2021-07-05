package ru.vlsu.practice.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlsu.practice.domain.News;
import ru.vlsu.practice.repository.NewsRepository;
import ru.vlsu.practice.service.NewsService;
import ru.vlsu.practice.service.dto.NewsDTO;
import ru.vlsu.practice.service.dto.PortalDTO;
import ru.vlsu.practice.service.mapper.NewsMapper;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    private final Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);

    private final NewsRepository newsRepository;

    private final NewsMapper newsMapper;

    public NewsServiceImpl(NewsRepository newsRepository, NewsMapper newsMapper) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
    }

    @Override
    public NewsDTO save(NewsDTO newsDTO) {
        log.debug("Request to save News : {}", newsDTO);
        News news = newsMapper.toEntity(newsDTO);
        news = newsRepository.save(news);
        return newsMapper.toDto(news);
    }

    @Override
    public Optional<NewsDTO> partialUpdate(NewsDTO newsDTO) {
        log.debug("Request to partially update News : {}", newsDTO);

        return newsRepository
            .findById(newsDTO.getId())
            .map(
                existingNews -> {
                    newsMapper.partialUpdate(existingNews, newsDTO);

                    return existingNews;
                }
            )
            .map(newsRepository::save)
            .map(newsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NewsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all News");
        return newsRepository.findAll(pageable).map(newsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NewsDTO> findOne(Long id) {
        log.debug("Request to get News : {}", id);
        return newsRepository.findById(id).map(newsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NewsDTO> findByName(String name) {
        log.debug("Request to get News by name : {}", name);
        return newsRepository.findByName(name).map(newsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete News : {}", id);
        newsRepository.deleteById(id);
    }
}
