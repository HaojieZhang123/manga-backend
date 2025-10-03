package org.project.java.manga_backend.repository;

import org.project.java.manga_backend.model.Manga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MangaRepository extends JpaRepository<Manga, Integer> {

}
