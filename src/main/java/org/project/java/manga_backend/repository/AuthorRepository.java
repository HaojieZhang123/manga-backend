package org.project.java.manga_backend.repository;

import org.project.java.manga_backend.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
