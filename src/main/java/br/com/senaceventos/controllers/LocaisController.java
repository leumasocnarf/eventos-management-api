package br.com.senaceventos.controllers;

import br.com.senaceventos.models.Local;
import br.com.senaceventos.services.LocaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LocaisController {

    private final LocaisService locaisService;

    @Autowired
    public LocaisController(LocaisService locaisService) {
        this.locaisService = locaisService;
    }

    @GetMapping("/locais")
    public String viewLocais(Model model) {
        model.addAttribute("listLocais", locaisService.retrieveAll());

        return "locais";
    }

    @GetMapping("/locais/create")
    public String viewLocaisCreateForm(Local local) {
        return "locais-create-form";
    }

    @PostMapping("/locais/save")
    public String saveLocal(@ModelAttribute Local local, Model model) {
        locaisService.append(local);

        model.addAttribute("listLocais", locaisService.retrieveAll());

        return "redirect:/locais";
    }

    @GetMapping("/locais/edit/{id}")
    public String editLocal(@PathVariable("id") Integer id, Model model){
        var local = locaisService.retrieveById(id);

        model.addAttribute("local", local);

        return "locais-edit-form";
    }

    @PostMapping("/locais/patch/{id}")
    public String putLocal(@PathVariable("id") Integer id, @ModelAttribute Local local, Model model) {
        locaisService.alter(id, local);

        return "redirect:/locais";
    }

    @GetMapping("/locais/delete/{id}")
    public String deleteLocal(@PathVariable("id") Integer id, Model model){
        locaisService.remove(id);

        model.addAttribute("listLocals", locaisService.retrieveAll());

        return "redirect:/locais";
    }
}
