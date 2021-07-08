package ru.vlsu.practice.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ru.vlsu.practice.domain.News;
import ru.vlsu.practice.domain.Portal;
import ru.vlsu.practice.domain.Todo;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>{

    Optional<News> findByName(String name);

}