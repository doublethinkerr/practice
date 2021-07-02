package ru.vlsu.practice.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.*;

public class PortalDTO implements Serializable{

    private Long id;

    @NotNull
    private String name;

    private String description;

    //private TypeEnum type;
    private String type;

    private List<NewsDTO> newsList;

    public List<NewsDTO> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<NewsDTO> newsList) {
        this.newsList = newsList;
    }

    @NotNull
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /*    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }*/

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PortalDTO)) {
            return false;
        }

        PortalDTO portalDTO = (PortalDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, portalDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PortalDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", deleted='" + getDeleted() +
            "}";
    }

}
