package org.project.java.manga_backend.service;

import java.util.List;
import java.util.Optional;

import org.project.java.manga_backend.model.Author;
import org.project.java.manga_backend.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Optional<Author> findById(Integer id) {
        return authorRepository.findById(id);
    }

    public Author getById(Integer id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isEmpty()) {
            return null;
        } else {
            return author.get();
        }
    }

    public Author store(Author author) {
        return authorRepository.save(author);
    }

    public Author update(Author author) {
        return authorRepository.save(author);
    }

    public void deleteById(Integer id) {
        authorRepository.deleteById(id);
    }

    public void delete(Author author) {
        authorRepository.delete(author);
    }

}
