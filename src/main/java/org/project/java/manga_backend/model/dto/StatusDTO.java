package org.project.java.manga_backend.model.dto;

import jakarta.validation.constraints.NotBlank;

public class StatusDTO {

    @NotBlank(message = "Release status name is required")
    private String name;

    private Integer[] mangaIds;

    public StatusDTO(String name, Integer[] mangaIds) {
        this.name = name;
        this.mangaIds = mangaIds;
    }

    // getters and setters

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
