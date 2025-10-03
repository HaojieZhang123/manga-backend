package org.project.java.manga_backend.service;

import java.util.List;
import java.util.Optional;

import org.project.java.manga_backend.model.Genre;
import org.project.java.manga_backend.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Optional<Genre> findById(Integer id) {
        return genreRepository.findById(id);
    }

    public Genre getById(Integer id) {
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isEmpty()) {
            return null;
        } else {
            return genre.get();
        }
    }

    public Genre store(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre update(Genre genre) {
        return genreRepository.save(genre);
    }

    public void deleteById(Integer id) {
        genreRepository.deleteById(id);
    }

    public void delete(Genre genre) {
        genreRepository.delete(genre);
    }

}
