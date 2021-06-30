package ru.vlsu.practice.service.dto;

import ru.vlsu.practice.domain.News;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;


public class NewsDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String description;

    private Instant newsDate;

    private Boolean important;

    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(Instant newsDate) {
        this.newsDate = newsDate;
    }

    public Boolean getImportant() {
        return important;
    }

    public void setImportant(Boolean important) {
        this.important = important;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean delete) {
        this.deleted = delete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NewsDTO)) {
            return false;
        }

        NewsDTO newsDTO = (NewsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, newsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TodoDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", newsDate='" + getNewsDate() + "'" +
            ", important='" + getImportant() + "'" +
            ", delete=" + getDeleted() +
            "}";
    }
}
