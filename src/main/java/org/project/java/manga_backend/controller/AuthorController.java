package org.project.java.manga_backend.controller;

import org.project.java.manga_backend.model.Author;
import org.project.java.manga_backend.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "authors/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("author", authorService.getById(id));
        return "authors/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("author", new Author());
        return "authors/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("author") Author author, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "authors/create-or-edit";
        } else {
            authorService.store(author);
            return "redirect:/authors";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("author", authorService.getById(id));
        model.addAttribute("edit", true);
        return "authors/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Integer id, @Valid @ModelAttribute("author") Author author,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "authors/create-or-edit";
        } else {
            authorService.update(author);
            return "redirect:/authors";
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        authorService.deleteById(id);
        return "redirect:/authors";
    }

}
