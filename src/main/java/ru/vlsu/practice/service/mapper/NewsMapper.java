package ru.vlsu.practice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.context.request.NativeWebRequest;
import ru.vlsu.practice.domain.News;
import ru.vlsu.practice.domain.Portal;
import ru.vlsu.practice.domain.Todo;
import ru.vlsu.practice.service.dto.NewsDTO;
import ru.vlsu.practice.service.dto.TodoDTO;

@Mapper(componentModel = "spring", uses = {PortalMapper.class})
public interface NewsMapper extends EntityMapper<NewsDTO, News>{

    @Mapping(target = "portal", source = "portalId")
    News toEntity(NewsDTO newsDTO);

    @Mapping(target = "portalId", source = "portal.id")
    NewsDTO toDto(News news);


}
