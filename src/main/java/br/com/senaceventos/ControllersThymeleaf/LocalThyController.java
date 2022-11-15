package br.com.senaceventos.ControllersThymeleaf;

import br.com.senaceventos.Entities.Local;
import br.com.senaceventos.Services.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LocalThyController {

    private final LocalService localService;

    @Autowired
    public LocalThyController(LocalService localService) {
        this.localService = localService;
    }

    @GetMapping("/locais")
    public String viewLocais(Model model) {
        model.addAttribute("listLocais", localService.retrieveAll());

        return "locais";
    }

    @GetMapping("/locais/create")
    public String viewLocaisCreateForm(Local local) {
        return "locais-create-form";
    }

    @PostMapping("/locais/save")
    public String saveLocal(@ModelAttribute Local local, Model model) {
        localService.append(local);

        model.addAttribute("listLocais", localService.retrieveAll());

        return "redirect:/locais";
    }

    @GetMapping("/locais/edit/{id}")
    public String editLocal(@PathVariable("id") Integer id, Model model){
        var local = localService.retrieveById(id);

        model.addAttribute("local", local);

        return "locais-edit-form";
    }

    @PostMapping("/locais/patch/{id}")
    public String putLocal(@PathVariable("id") Integer id, @ModelAttribute Local local, Model model) {
        localService.alter(id, local);

        return "redirect:/locais";
    }

    @GetMapping("/locais/delete/{id}")
    public String deleteLocal(@PathVariable("id") Integer id, Model model){
        localService.remove(id);

        model.addAttribute("listLocals", localService.retrieveAll());

        return "redirect:/locais";
    }
}
