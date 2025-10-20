package org.project.java.manga_backend.controller.api;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.project.java.manga_backend.model.Author;
import org.project.java.manga_backend.model.Manga;
import org.project.java.manga_backend.model.dto.AuthorDTO;
import org.project.java.manga_backend.service.AuthorService;
import org.project.java.manga_backend.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/authors")
public class AuthorRestController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private MangaService mangaService;

    // index
    @GetMapping
    public ResponseEntity<List<Author>> index() {
        List<Author> authorList = authorService.findAll();
        if (authorList.isEmpty()) {
            return new ResponseEntity<List<Author>>(HttpStatusCode.valueOf(404)); // not fund
        }
        return new ResponseEntity<List<Author>>(authorList, HttpStatusCode.valueOf(200)); // OK
    }

    // show
    @GetMapping("/{id}")
    public ResponseEntity<Author> show(@PathVariable("id") Integer id) {
        Optional<Author> author = authorService.findById(id);
        if (author.isEmpty()) {
            return new ResponseEntity<Author>(HttpStatusCode.valueOf(404)); // not found
        }
        return new ResponseEntity<Author>(author.get(), HttpStatusCode.valueOf(200)); // OK
    }

    // create
    @PostMapping
    public ResponseEntity<Author> create(@Valid @RequestBody AuthorDTO author,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Author>(HttpStatusCode.valueOf(400)); // bad request
        } else {
            Author newAuthor = new Author();
            newAuthor.setName(author.getName());

            if (author.getMangaIds().length > 0) {
                HashSet<Manga> linkedManga = new HashSet<>();
                for (Integer mangaId : author.getMangaIds()) {
                    Optional<Manga> manga = mangaService.findById(mangaId);
                    if (manga.isPresent()) {
                        linkedManga.add(manga.get());
                    }
                }
                newAuthor.setMangas(linkedManga);
            }

            authorService.store(newAuthor);

            return new ResponseEntity<Author>(newAuthor, HttpStatusCode.valueOf(201)); // created
        }
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable("id") Integer id,
            @Valid @RequestBody AuthorDTO author,
            BindingResult bindingResult) {
        if (authorService.findById(id).isEmpty()) {
            return new ResponseEntity<Author>(HttpStatusCode.valueOf(404)); // not found
        } else if (bindingResult.hasErrors()) {
            return new ResponseEntity<Author>(HttpStatusCode.valueOf(400)); // bad request
        }

        Author authorToUpdate = authorService.findById(id).get();
        authorToUpdate.setName(author.getName());

        if (author.getMangaIds().length > 0) {
            HashSet<Manga> linkedManga = new HashSet<>();
            for (Integer mangaId : author.getMangaIds()) {
                Optional<Manga> manga = mangaService.findById(mangaId);
                if (manga.isPresent()) {
                    linkedManga.add(manga.get());
                }
            }
            authorToUpdate.setMangas(linkedManga);
        }

        authorService.update(authorToUpdate);

        return new ResponseEntity<Author>(authorToUpdate, HttpStatusCode.valueOf(200)); // OK
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Author> delete(@PathVariable("id") Integer id) {
        if (authorService.findById(id).isEmpty()) {
            return new ResponseEntity<Author>(HttpStatusCode.valueOf(404)); // not found
        }
        authorService.deleteById(id);
        return new ResponseEntity<Author>(HttpStatusCode.valueOf(204)); // no content
    }

}
