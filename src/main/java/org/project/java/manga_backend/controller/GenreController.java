package org.project.java.manga_backend.controller;

import org.project.java.manga_backend.model.Genre;
import org.project.java.manga_backend.service.GenreService;
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
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("genres", genreService.findAll());
        return "genres/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("genre", genreService.getById(id));
        return "genres/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("genre", new Genre());
        return "genres/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("genre") Genre genre, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "genres/create-or-edit";
        } else {
            genreService.store(genre);
            return "redirect:/genres";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("genre", genreService.getById(id));
        model.addAttribute("edit", true);
        return "genres/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Integer id, @Valid @ModelAttribute("genre") Genre genre,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "genres/create-or-edit";
        } else {
            genreService.update(genre);
            return "redirect:/genres";
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        genreService.deleteById(id);
        return "redirect:/genres";
    }

}
