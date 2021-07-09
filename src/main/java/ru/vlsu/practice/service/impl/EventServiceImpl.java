package ru.vlsu.practice.service.impl;

import java.util.ArrayList;
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
import ru.vlsu.practice.exception.BindEventToDeletedPlaceException;
import ru.vlsu.practice.repository.EventRepository;
import ru.vlsu.practice.repository.PlaceRepository;
import ru.vlsu.practice.service.EventService;
import ru.vlsu.practice.service.dto.EventDTO;
import ru.vlsu.practice.service.mapper.EventMapper;

/**
 * Service Implementation for managing {@link Event}.
 */
@Service
@Transactional
public class EventServiceImpl implements EventService {

    private final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

    private final EventRepository eventRepository;

    private final EventMapper eventMapper;

    private final PlaceRepository placeRepository;

    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper, PlaceRepository placeRepository) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.placeRepository=placeRepository;
    }

    @Override
    public EventDTO save(EventDTO eventDTO) {
        log.debug("Request to save Event : {}", eventDTO);

        if (placeRepository.findById(eventDTO.getPlaceId()).get().getDeleted() == true){
            throw new BindEventToDeletedPlaceException();
        }

        Event event = eventMapper.toEntity(eventDTO);
        event = eventRepository.save(event);
        return eventMapper.toDto(event);
    }

    @Override
    public Optional<EventDTO> partialUpdate(EventDTO eventDTO) {
        log.debug("Request to partially update Event : {}", eventDTO);

        return eventRepository
            .findById(eventDTO.getId())
            .map(
                existingEvent -> {
                    eventMapper.partialUpdate(existingEvent, eventDTO);

                    return existingEvent;
                }
            )
            .map(eventRepository::save)
            .map(eventMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EventDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Events");

        List<Event> list = eventRepository.findAllByDeleted(false, pageable);
        Page<Event> page = new PageImpl<>(list);

        return page.map(eventMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EventDTO> findOne(Long id) {
        log.debug("Request to get Event : {}", id);
        return eventRepository.findById(id).map(eventMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Event : {}", id);

        Optional<Event> eventDTO = eventRepository.findById(id);
        if(eventDTO.isPresent()){
            eventRepository.findById(id).get().setDeleted(true);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EventDTO> findAllByName(String name) {
        log.debug("Request to get Event by name : {}", name);
        List<Event> list = eventRepository.findAllByName(name);
        Page<Event> page = new PageImpl<>(list);

        return page.map(eventMapper::toDto);
    }

}
