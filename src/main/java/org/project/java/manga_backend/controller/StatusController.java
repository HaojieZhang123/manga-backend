package org.project.java.manga_backend.controller;

import org.project.java.manga_backend.model.Status;
import org.project.java.manga_backend.service.StatusService;
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
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("statuses", statusService.findAll());
        return "status/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("status", statusService.getById(id));
        return "status/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("status", new Status());
        return "status/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("status") Status status, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "status/create-or-edit";
        } else {
            statusService.store(status);
            return "redirect:/status";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("status", statusService.getById(id));
        model.addAttribute("edit", true);
        return "status/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Integer id, @Valid @ModelAttribute("status") Status status,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "status/create-or-edit";
        } else {
            statusService.update(status);
            return "redirect:/status";
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        statusService.deleteById(id);
        return "redirect:/status";
    }

}
