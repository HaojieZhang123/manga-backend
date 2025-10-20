package org.project.java.manga_backend.controller.api;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.project.java.manga_backend.model.Manga;
import org.project.java.manga_backend.model.Status;
import org.project.java.manga_backend.model.dto.StatusDTO;
import org.project.java.manga_backend.service.MangaService;
import org.project.java.manga_backend.service.StatusService;
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
@RequestMapping("/api/status")
public class StatusRestController {

    @Autowired
    private StatusService statusService;

    @Autowired
    private MangaService mangaService;

    @GetMapping
    public ResponseEntity<List<Status>> index() {
        List<Status> statusList = statusService.findAll();
        if (statusList.isEmpty()) {
            return new ResponseEntity<List<Status>>(HttpStatusCode.valueOf(404)); // not fund
        }
        return new ResponseEntity<List<Status>>(statusList, HttpStatusCode.valueOf(200)); // OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<Status> show(@PathVariable("id") Integer id) {
        Optional<Status> status = statusService.findById(id);
        if (status.isEmpty()) {
            return new ResponseEntity<Status>(HttpStatusCode.valueOf(404)); // not found
        }
        return new ResponseEntity<Status>(status.get(), HttpStatusCode.valueOf(200)); // OK
    }

    @PostMapping
    public ResponseEntity<Status> create(@Valid @RequestBody StatusDTO status,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Status>(HttpStatusCode.valueOf(400)); // bad request
        }

        Status newStatus = new Status();
        newStatus.setName(status.getName());

        if (status.getMangaIds().length > 0) {
            HashSet<Manga> linkedManga = new HashSet<>();
            for (Integer mangaId : status.getMangaIds()) {
                Optional<Manga> manga = mangaService.findById(mangaId);
                if (manga.isPresent()) {
                    linkedManga.add(manga.get());
                }
            }
            newStatus.setMangas(linkedManga);
        }

        statusService.store(newStatus);

        return new ResponseEntity<Status>(newStatus, HttpStatusCode.valueOf(201)); // created
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<Status> update(@PathVariable("id") Integer id,
            @Valid @RequestBody StatusDTO status,
            BindingResult bindingResult) {
        if (statusService.findById(id).isEmpty()) {
            return new ResponseEntity<Status>(HttpStatusCode.valueOf(404)); // not found
        } else if (bindingResult.hasErrors()) {
            return new ResponseEntity<Status>(HttpStatusCode.valueOf(400)); // bad request
        }

        Status statusToUpdate = statusService.findById(id).get();
        statusToUpdate.setName(status.getName());

        if (status.getMangaIds().length > 0) {
            HashSet<Manga> linkedManga = new HashSet<>();
            for (Integer mangaId : status.getMangaIds()) {
                Optional<Manga> manga = mangaService.findById(mangaId);
                if (manga.isPresent()) {
                    linkedManga.add(manga.get());
                }
            }
            statusToUpdate.setMangas(linkedManga);
        }

        statusService.update(statusToUpdate);

        return new ResponseEntity<Status>(statusToUpdate, HttpStatusCode.valueOf(200)); // OK
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Status> delete(@PathVariable("id") Integer id) {
        if (statusService.findById(id).isEmpty()) {
            return new ResponseEntity<Status>(HttpStatusCode.valueOf(404)); // not found
        }
        statusService.deleteById(id);
        return new ResponseEntity<Status>(HttpStatusCode.valueOf(204)); // no content
    }
}
