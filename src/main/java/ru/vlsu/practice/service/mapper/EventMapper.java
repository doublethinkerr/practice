package ru.vlsu.practice.service.mapper;

import org.mapstruct.*;
import ru.vlsu.practice.domain.*;
import ru.vlsu.practice.service.dto.EventDTO;

/**
 * Mapper for the entity {@link Event} and its DTO {@link EventDTO}.
 */
@Mapper(componentModel = "spring", uses = {PlaceMapper.class})
public interface EventMapper extends EntityMapper<EventDTO, Event> {}
