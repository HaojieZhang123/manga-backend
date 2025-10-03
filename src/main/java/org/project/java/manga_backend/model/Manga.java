package org.project.java.manga_backend.model;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "manga")
public class Manga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    @Lob
    private String description;

    @NotBlank(message = "Release date is required")
    @PastOrPresent(message = "Release date must be in the past or present")
    LocalDate releaseDate;

    // many to many relation: authors
    @ManyToMany
    @JoinTable(name = "author_manga", joinColumns = @JoinColumn(name = "manga_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    @JsonManagedReference
    private Set<Author> authors;

    // many to many relation: genres
    @ManyToMany
    @JoinTable(name = "genre_manga", joinColumns = @JoinColumn(name = "manga_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @JsonManagedReference
    private Set<Genre> genres;

    // manu to one relation. release status (ongoing, finished, hiatus, cancelled)
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
