package org.project.java.manga_backend.controller;

import org.project.java.manga_backend.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/manga")
public class MangaController {

    @Autowired
    private MangaService mangaService;

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

}
