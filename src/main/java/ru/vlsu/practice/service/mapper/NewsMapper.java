package ru.vlsu.practice.service.mapper;

import org.mapstruct.Mapper;
import ru.vlsu.practice.domain.News;
import ru.vlsu.practice.domain.Portal;
import ru.vlsu.practice.domain.Todo;
import ru.vlsu.practice.service.dto.NewsDTO;
import ru.vlsu.practice.service.dto.TodoDTO;

@Mapper(componentModel = "spring", uses = {PortalMapper.class})
public interface NewsMapper extends EntityMapper<NewsDTO, News>{

}
