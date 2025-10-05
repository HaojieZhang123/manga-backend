package org.project.java.manga_backend.model.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthorDTO {

    @NotBlank(message = "Name is required")
    private String name;

    private Integer[] mangaIds;

    public AuthorDTO(String name, Integer[] mangaIds) {
        this.name = name;
        this.mangaIds = mangaIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer[] getMangaIds() {
        return mangaIds;
    }

    public void setMangaIds(Integer[] mangaIds) {
        this.mangaIds = mangaIds;
    }

}
