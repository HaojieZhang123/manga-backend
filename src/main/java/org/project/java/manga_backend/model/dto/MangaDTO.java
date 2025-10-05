package org.project.java.manga_backend.model.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class MangaDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Release date is required")
    @PastOrPresent(message = "Release date must be in the past or present")
    private LocalDate releaseDate;

    private Integer[] authorIds;
    private Integer[] genreIds;
    private Integer statusId;

    public MangaDTO(String title, String description, LocalDate releaseDate, Integer[] authorIds, Integer[] genreIds,
            Integer statusId) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.authorIds = authorIds;
        this.genreIds = genreIds;
        this.statusId = statusId;
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

    public Integer[] getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(Integer[] authorIds) {
        this.authorIds = authorIds;
    }

    public Integer[] getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(Integer[] genreIds) {
        this.genreIds = genreIds;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

}
