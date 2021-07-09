package ru.vlsu.practice.service.impl;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlsu.practice.domain.Event;
import ru.vlsu.practice.domain.Place;
import ru.vlsu.practice.repository.PlaceRepository;
import ru.vlsu.practice.service.PlaceService;
import ru.vlsu.practice.service.dto.PlaceDTO;
import ru.vlsu.practice.service.mapper.PlaceMapper;

/**
 * Service Implementation for managing {@link Place}.
 */
@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

    private final Logger log = LoggerFactory.getLogger(PlaceServiceImpl.class);

    private final PlaceRepository placeRepository;

    private final PlaceMapper placeMapper;

    public PlaceServiceImpl(PlaceRepository placeRepository, PlaceMapper placeMapper) {
        this.placeRepository = placeRepository;
        this.placeMapper = placeMapper;
    }

    @Override
    public PlaceDTO save(PlaceDTO placeDTO) {
        log.debug("Request to save Place : {}", placeDTO);
        Place place = placeMapper.toEntity(placeDTO);
        place = placeRepository.save(place);
        return placeMapper.toDto(place);
    }

    @Override
    public Optional<PlaceDTO> partialUpdate(PlaceDTO placeDTO) {
        log.debug("Request to partially update Place : {}", placeDTO);

        return placeRepository
            .findById(placeDTO.getId())
            .map(
                existingPlace -> {
                    placeMapper.partialUpdate(existingPlace, placeDTO);

                    return existingPlace;
                }
            )
            .map(placeRepository::save)
            .map(placeMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<PlaceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Places");

        List<Place> list = placeRepository.findAllByDeleted(false, pageable);
        Page<Place> page = new PageImpl<>(list);
        return page.map(placeMapper::toDto);

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PlaceDTO> findOne(Long id) {
        log.debug("Request to get Place : {}", id);
        return placeRepository.findById(id).map(placeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request delete Place : {}", id);

        Optional<Place> placeDTO = placeRepository.findById(id);
        if(placeDTO.isPresent()) {
            placeRepository.findById(id).get().setDeleted(true);
            for (Event event : placeDTO.get().getEventsList()) {
                event.setDeleted(true);
            }
            placeRepository.save(placeDTO.get());
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Page<PlaceDTO> findAllByName(String name) {
        List<Place> list = placeRepository.findAllByName(name);
        Page<Place> page = new PageImpl<>(list);

        return page.map(placeMapper::toDto);
    }

}

