package org.project.java.manga_backend.controller.api;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.project.java.manga_backend.model.Genre;
import org.project.java.manga_backend.model.Manga;
import org.project.java.manga_backend.model.dto.GenreDTO;
import org.project.java.manga_backend.service.GenreService;
import org.project.java.manga_backend.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import jakarta.validation.Valid;

public class GenreRestController {

    @Autowired
    private GenreService genreService;

    @Autowired
    private MangaService mangaService;

    // index
    @GetMapping
    public ResponseEntity<List<Genre>> index() {
        List<Genre> genreList = genreService.findAll();
        if (genreList.isEmpty()) {
            return new ResponseEntity<List<Genre>>(HttpStatusCode.valueOf(404)); // not fund
        }
        return new ResponseEntity<List<Genre>>(genreList, HttpStatusCode.valueOf(200)); // OK
    }

    // show
    @GetMapping("/{id}")
    public ResponseEntity<Genre> show(@PathVariable("id") Integer id) {
        Optional<Genre> genre = genreService.findById(id);
        if (genre.isEmpty()) {
            return new ResponseEntity<Genre>(HttpStatusCode.valueOf(404)); // not found
        }
        return new ResponseEntity<Genre>(genre.get(), HttpStatusCode.valueOf(200)); // OK
    }

    // create
    @PostMapping
    public ResponseEntity<Genre> create(@Valid @ModelAttribute("genre") GenreDTO genre,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Genre>(HttpStatusCode.valueOf(400)); // bad request
        } else {
            Genre newGenre = new Genre();
            newGenre.setName(genre.getName());
            genreService.store(newGenre);

            HashSet<Manga> linkedManga = new HashSet<>();
            for (Integer mangaId : genre.getMangaIds()) {
                Optional<Manga> manga = mangaService.findById(mangaId);
                if (manga.isPresent()) {
                    linkedManga.add(manga.get());
                }
            }
            newGenre.setMangas(linkedManga);

            return new ResponseEntity<Genre>(newGenre, HttpStatusCode.valueOf(201)); // created
        }
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<Genre> update(@PathVariable("id") Integer id,
            @Valid @ModelAttribute("genre") GenreDTO genre,
            BindingResult bindingResult) {
        if (genreService.findById(id).isEmpty()) {
            return new ResponseEntity<Genre>(HttpStatusCode.valueOf(404)); // not found
        } else if (bindingResult.hasErrors()) {
            return new ResponseEntity<Genre>(HttpStatusCode.valueOf(400)); // bad request
        }

        Genre genreToUpdate = genreService.findById(id).get();
        genreToUpdate.setName(genre.getName());

        HashSet<Manga> linkedManga = new HashSet<>();
        for (Integer mangaId : genre.getMangaIds()) {
            Optional<Manga> manga = mangaService.findById(mangaId);
            if (manga.isPresent()) {
                linkedManga.add(manga.get());
            }
        }
        genreToUpdate.setMangas(linkedManga);

        return new ResponseEntity<Genre>(genreToUpdate, HttpStatusCode.valueOf(200)); // OK
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Genre> delete(@PathVariable("id") Integer id) {
        if (genreService.findById(id).isEmpty()) {
            return new ResponseEntity<Genre>(HttpStatusCode.valueOf(404)); // not found
        }
        genreService.deleteById(id);
        return new ResponseEntity<Genre>(HttpStatusCode.valueOf(204)); // no content
    }

}
