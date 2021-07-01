package ru.vlsu.practice.service.mapper;

import org.mapstruct.*;
import ru.vlsu.practice.domain.*;
import ru.vlsu.practice.service.dto.PlaceDTO;

/**
 * Mapper for the entity {@link Place} and its DTO {@link PlaceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PlaceMapper extends EntityMapper<PlaceDTO, Place> {}
