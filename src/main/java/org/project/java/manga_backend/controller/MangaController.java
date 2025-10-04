package org.project.java.manga_backend.controller;

import org.project.java.manga_backend.model.Manga;
import org.project.java.manga_backend.service.AuthorService;
import org.project.java.manga_backend.service.GenreService;
import org.project.java.manga_backend.service.MangaService;
import org.project.java.manga_backend.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/manga")
public class MangaController {

    @Autowired
    private MangaService mangaService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private StatusService statusService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("mangas", mangaService.findAll());
        return "manga/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("manga", mangaService.getById(id));
        return "manga/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("manga", new Manga());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        return "manga/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("manga") Manga manga, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("genres", genreService.findAll());
            model.addAttribute("statuses", statusService.findAll());
            return "manga/create-or-edit";
        } else {
            mangaService.store(manga);
            return "redirect:/manga";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("manga", mangaService.getById(id));
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("statuses", statusService.findAll());
        model.addAttribute("edit", true);
        return "manga/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Integer id, @Valid @ModelAttribute("manga") Manga manga,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("genres", genreService.findAll());
            model.addAttribute("statuses", statusService.findAll());
            model.addAttribute("edit", true);
            return "manga/create-or-edit";
        } else {
            mangaService.update(manga);
            return "redirect:/manga";
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        mangaService.deleteById(id);
        return "redirect:/manga";
    }

}
