package ru.vlsu.practice.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link ru.vlsu.practice.domain.Place} entity.
 */
public class PlaceDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String administrator;

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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdministrator() {
        return administrator;
    }

    public void setAdministrator(String administrator) {
        this.administrator = administrator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlaceDTO)) {
            return false;
        }

        PlaceDTO placeDTO = (PlaceDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, placeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PlaceDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", administrator='" + getAdministrator() + "'" +
            ", deleted='" + getDeleted() + "'" +
            "}";
    }
}

