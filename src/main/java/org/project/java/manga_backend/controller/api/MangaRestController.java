package org.project.java.manga_backend.controller.api;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.project.java.manga_backend.model.Author;
import org.project.java.manga_backend.model.Genre;
import org.project.java.manga_backend.model.Manga;
import org.project.java.manga_backend.model.Status;
import org.project.java.manga_backend.model.dto.MangaDTO;
import org.project.java.manga_backend.service.AuthorService;
import org.project.java.manga_backend.service.GenreService;
import org.project.java.manga_backend.service.MangaService;
import org.project.java.manga_backend.service.StatusService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/manga")
public class MangaRestController {

    @Autowired
    private MangaService mangaService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private StatusService statusService;

    // index
    @GetMapping
    public ResponseEntity<List<Manga>> index() {
        List<Manga> mangaList = mangaService.findAll();
        if (mangaList.isEmpty()) {
            return new ResponseEntity<List<Manga>>(HttpStatusCode.valueOf(404)); // not fund
        }
        return new ResponseEntity<List<Manga>>(mangaList, HttpStatusCode.valueOf(200)); // OK
    }

    // show
    @GetMapping("/{id}")
    public ResponseEntity<Manga> show(@PathVariable("id") Integer id) {
        Optional<Manga> manga = mangaService.findById(id);
        if (manga.isEmpty()) {
            return new ResponseEntity<Manga>(HttpStatusCode.valueOf(404)); // not found
        }
        return new ResponseEntity<Manga>(manga.get(), HttpStatusCode.valueOf(200)); // OK
    }

    // create
    @PostMapping
    public ResponseEntity<Manga> create(@Valid @ModelAttribute("manga") MangaDTO manga, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Manga>(HttpStatusCode.valueOf(400)); // bad request
        }
        Manga newManga = new Manga();
        newManga.setTitle(manga.getTitle());
        newManga.setDescription(manga.getDescription());
        newManga.setReleaseDate(manga.getReleaseDate());

        HashSet<Author> newAuthors = new HashSet<>();
        for (Integer authorId : manga.getAuthorIds()) {
            Optional<Author> author = authorService.findById(authorId);
            if (author.isPresent()) {
                newAuthors.add(author.get());
            }
        }
        newManga.setAuthors(newAuthors);

        HashSet<Genre> newGenres = new HashSet<>();
        for (Integer genreId : manga.getGenreIds()) {
            Optional<Genre> genre = genreService.findById(genreId);
            if (genre.isPresent()) {
                newGenres.add(genre.get());
            }
        }
        newManga.setGenres(newGenres);

        Optional<Status> newStatus = statusService.findById(manga.getStatusId());
        if (newStatus.isPresent()) {
            newManga.setStatus(newStatus.get());
        }

        return new ResponseEntity<Manga>(mangaService.store(newManga), HttpStatusCode.valueOf(201)); // created
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<Manga> update(@PathVariable("id") Integer id, @Valid @ModelAttribute("manga") MangaDTO manga,
            BindingResult bindingResult) {
        if (mangaService.findById(id).isEmpty()) {
            return new ResponseEntity<Manga>(HttpStatusCode.valueOf(404)); // not found
        } else if (bindingResult.hasErrors()) {
            return new ResponseEntity<Manga>(HttpStatusCode.valueOf(400)); // bad request
        }

        Manga mangaToUpdate = mangaService.findById(id).get();
        mangaToUpdate.setTitle(manga.getTitle());
        mangaToUpdate.setDescription(manga.getDescription());
        mangaToUpdate.setReleaseDate(manga.getReleaseDate());

        HashSet<Author> newAuthors = new HashSet<>();
        for (Integer authorId : manga.getAuthorIds()) {
            Optional<Author> author = authorService.findById(authorId);
            if (author.isPresent()) {
                newAuthors.add(author.get());
            }
        }
        mangaToUpdate.setAuthors(newAuthors);

        HashSet<Genre> newGenres = new HashSet<>();
        for (Integer genreId : manga.getGenreIds()) {
            Optional<Genre> genre = genreService.findById(genreId);
            if (genre.isPresent()) {
                newGenres.add(genre.get());
            }
        }
        mangaToUpdate.setGenres(newGenres);

        Optional<Status> newStatus = statusService.findById(manga.getStatusId());
        if (newStatus.isPresent()) {
            mangaToUpdate.setStatus(newStatus.get());
        }

        return new ResponseEntity<Manga>(mangaService.update(mangaToUpdate), HttpStatusCode.valueOf(200)); // OK
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Manga> delete(@PathVariable("id") Integer id) {
        if (mangaService.findById(id).isEmpty()) {
            return new ResponseEntity<Manga>(HttpStatusCode.valueOf(404)); // not found
        }
        mangaService.deleteById(id);
        return new ResponseEntity<Manga>(HttpStatusCode.valueOf(204)); // no content
    }

}
