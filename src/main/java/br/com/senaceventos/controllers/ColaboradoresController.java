package br.com.senaceventos.controllers;

import br.com.senaceventos.models.Colaborador;
import br.com.senaceventos.services.ColaboradoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ColaboradoresController {

    private final ColaboradoresService colaboradoresService;

    @Autowired
    public ColaboradoresController(ColaboradoresService colaboradoresService) {
        this.colaboradoresService = colaboradoresService;
    }

    @GetMapping("/colaboradores")
    public String viewColaboradores(Model model) {
        model.addAttribute("listColaboradores", colaboradoresService.retrieveAll());
        return "colaboradores";
    }

    @GetMapping("/colaboradores/create")
    public String viewColaboradorCreateForm(Colaborador colaborador) {
        return "colaboradores-create-form";
    }

    @PostMapping("/colaboradores/save")
    public String saveColaborador(@ModelAttribute Colaborador colaborador, Model model) {
        colaboradoresService.append(colaborador);

        model.addAttribute("listColaboradores", colaboradoresService.retrieveAll());

        return "redirect:/colaboradores";
    }

    @GetMapping("/colaboradores/edit/{id}")
    public String editColaborador(@PathVariable("id") Integer id, Model model){
        var colaborador = colaboradoresService.retrieveById(id);

        model.addAttribute("colaborador", colaborador);

        return "colaboradores-edit-form";
    }

    @PostMapping("/colaboradores/patch/{id}")
    public String putColaborador(@PathVariable("id") Integer id, @ModelAttribute Colaborador colaborador, Model model) {
        colaboradoresService.alter(id, colaborador);

        return "redirect:/colaboradores";
    }

    @GetMapping("/colaboradores/delete/{id}")
    public String deleteColaborador(@PathVariable("id") Integer id, Model model){
        colaboradoresService.remove(id);

        model.addAttribute("listColaboradors", colaboradoresService.retrieveAll());

        return "redirect:/colaboradores";
    }
}
