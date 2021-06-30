package ru.vlsu.practice.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "news")
public class News implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "newsname", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "news_date")
    private Instant newsDate;

    @Column(name = "isimportant")
    private Boolean important;

    @Column(name = "isdeleted")
    private Boolean deleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public News name(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public News description(String name) {
        this.name = name;
        return this;
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

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof News)) {
            return false;
        }
        return id != null && id.equals(((News) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "News{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", newsDate='" + getNewsDate() + "'" +
            ", important=" + getImportant() + "'" +
            ", deleted='" + getDeleted() +
            "}";
    }

}
