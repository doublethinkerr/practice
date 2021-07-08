package ru.vlsu.practice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlsu.practice.domain.News;
import ru.vlsu.practice.domain.Portal;
import ru.vlsu.practice.repository.PortalRepository;
import ru.vlsu.practice.service.PortalService;
import ru.vlsu.practice.service.dto.PortalDTO;
import ru.vlsu.practice.service.mapper.PortalMapper;

import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PortalServiceImpl  implements PortalService {

    private final Logger log = LoggerFactory.getLogger(PortalServiceImpl.class);

    private final PortalRepository portalRepository;

    private final PortalMapper portalMapper;

    public PortalServiceImpl(PortalRepository portalRepository, PortalMapper portalMapper) {
        this.portalRepository = portalRepository;
        this.portalMapper = portalMapper;
    }

    @Override
    public PortalDTO save(PortalDTO portalDTO) {
        log.debug("Request to save Portal : {}", portalDTO);
        Portal portal = portalMapper.toEntity(portalDTO);
        portal = portalRepository.save(portal);
        return portalMapper.toDto(portal);
    }

    @Override
    public Optional<PortalDTO> partialUpdate(PortalDTO portalDTO) {
        log.debug("Request to partially update Portal : {}", portalDTO);

        return portalRepository
            .findById(portalDTO.getId())
            .map(
                existingPortal -> {
                    portalMapper.partialUpdate(existingPortal, portalDTO);

                    return existingPortal;
                }
            )
            .map(portalRepository::save)
            .map(portalMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PortalDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Portals");

        List<Portal> list = portalRepository.findAllByDeleted(false, pageable);
        Page<Portal> page = new PageImpl<>(list);
        return page.map(portalMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PortalDTO> findOne(Long id) {
        log.debug("Request to get Portal : {}", id);
        return portalRepository.findById(id).map(portalMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PortalDTO> findByName(String name) {
        log.debug("Request to get Portal by name : {}", name);
        return portalRepository.findByName(name).map(portalMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Portal : {}", id);

        Optional<Portal> portal = portalRepository.findById(id);
        if(portal.isPresent()){
            portalRepository.findById(id).get().setDeleted(true);
            for (News news : portal.get().getNewsList()){
                news.setDeleted(true);
            }
            portalRepository.save(portal.get());
        }



    }

}
