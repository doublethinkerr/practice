package ru.vlsu.practice.service.mapper;

import org.mapstruct.*;
import ru.vlsu.practice.domain.*;
import ru.vlsu.practice.service.dto.PortalDTO;

@Mapper(componentModel = "spring", uses = {NewsMapper.class})
public interface PortalMapper extends EntityMapper<PortalDTO, Portal>{

    default Portal fromId(Long id) {
        if (id == null) {
            return null;
        }
        Portal portal = new Portal();
        portal.setId(id);
        return portal;
    }

}
