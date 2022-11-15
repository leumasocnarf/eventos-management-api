package br.com.senaceventos.ControllersThymeleaf;

import br.com.senaceventos.Entities.Colaborador;
import br.com.senaceventos.Services.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ColaboradorThyController {

    private final ColaboradorService colaboradorService;

    @Autowired
    public ColaboradorThyController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @GetMapping("/colaboradores")
    public String viewColaboradores(Model model) {
        model.addAttribute("listColaboradores", colaboradorService.retrieveAll());
        return "colaboradores";
    }

    @GetMapping("/colaboradores/create")
    public String viewColaboradorCreateForm(Colaborador colaborador) {
        return "colaboradores-create-form";
    }

    @PostMapping("/colaboradores/save")
    public String saveColaborador(@ModelAttribute Colaborador colaborador, Model model) {
        colaboradorService.append(colaborador);

        model.addAttribute("listColaboradores", colaboradorService.retrieveAll());

        return "redirect:/colaboradores";
    }

    @GetMapping("/colaboradores/edit/{id}")
    public String editColaborador(@PathVariable("id") Integer id, Model model){
        var colaborador = colaboradorService.retrieveById(id);

        model.addAttribute("colaborador", colaborador);

        return "colaboradores-edit-form";
    }

    @PostMapping("/colaboradores/patch/{id}")
    public String putColaborador(@PathVariable("id") Integer id, @ModelAttribute Colaborador colaborador, Model model) {
        colaboradorService.alter(id, colaborador);

        return "redirect:/colaboradores";
    }

    @GetMapping("/colaboradores/delete/{id}")
    public String deleteColaborador(@PathVariable("id") Integer id, Model model){
        colaboradorService.remove(id);

        model.addAttribute("listColaboradors", colaboradorService.retrieveAll());

        return "redirect:/colaboradores";
    }
}
