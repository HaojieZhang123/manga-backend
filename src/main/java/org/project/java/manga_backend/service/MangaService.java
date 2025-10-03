package org.project.java.manga_backend.service;

import java.util.List;
import java.util.Optional;

import org.project.java.manga_backend.model.Manga;
import org.project.java.manga_backend.repository.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MangaService {

    @Autowired
    private MangaRepository mangaRepository;

    public List<Manga> findAll() {
        return mangaRepository.findAll();
    }

    public Optional<Manga> findById(Integer id) {
        return mangaRepository.findById(id);
    }

    public Manga getById(Integer id) {
        Optional<Manga> manga = mangaRepository.findById(id);
        if (manga.isEmpty()) {
            return null;
        } else {
            return manga.get();
        }
    }

    public Manga store(Manga manga) {
        return mangaRepository.save(manga);
    }

    public Manga update(Manga manga) {
        return mangaRepository.save(manga);
    }

    public void deleteById(Integer id) {
        mangaRepository.deleteById(id);
    }

    public void delete(Manga manga) {
        mangaRepository.delete(manga);
    }

}
