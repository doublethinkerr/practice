package ru.vlsu.practice.service.impl;

import java.net.BindException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlsu.practice.domain.News;
import ru.vlsu.practice.exception.BindNewsToDeletedPortalException;
import ru.vlsu.practice.repository.NewsRepository;
import ru.vlsu.practice.repository.PortalRepository;
import ru.vlsu.practice.service.NewsService;
import ru.vlsu.practice.service.dto.NewsDTO;
import ru.vlsu.practice.service.mapper.NewsMapper;
import ru.vlsu.practice.web.rest.errors.BadRequestAlertException;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    private final Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);

    private final NewsRepository newsRepository;

    private final NewsMapper newsMapper;

    private final PortalRepository portalRepository;

    public NewsServiceImpl(NewsRepository newsRepository, NewsMapper newsMapper, PortalRepository portalRepository) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
        this.portalRepository = portalRepository;
    }

    @Override
    public NewsDTO save(NewsDTO newsDTO) throws Exception {
        log.debug("Request to save News : {}", newsDTO);
        if (portalRepository.findById(newsDTO.getPortalId()).get().getDeleted() == true){
            throw new BindNewsToDeletedPortalException();
        }
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

        List<News> list = newsRepository.findAllByDeleted(false, pageable);
        Page<News> page = new PageImpl<>(list);
        return page.map(newsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NewsDTO> findOne(Long id) {
        log.debug("Request to get News : {}", id);
        return newsRepository.findById(id).map(newsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NewsDTO> findAllByName(String name) {
        log.debug("Request to get News by name : {}", name);
        List<News> list = newsRepository.findAllByName(name);
        Page<News> page = new PageImpl<>(list);
        return page.map(newsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete News : {}", id);
        Optional<News> newsDTO = newsRepository.findById(id);
        if(newsDTO.isPresent()){
            newsRepository.findById(id).get().setDeleted(true);
        }
    }


}
