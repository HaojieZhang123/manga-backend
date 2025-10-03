package org.project.java.manga_backend.repository;

import org.project.java.manga_backend.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
