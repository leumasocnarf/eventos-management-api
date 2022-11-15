package br.com.senaceventos.ControllersThymeleaf;

import br.com.senaceventos.Entities.Equipamento;
import br.com.senaceventos.Services.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class EquipamentoThyController {

    private final EquipamentoService equipamentoService;

    @Autowired
    public EquipamentoThyController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @GetMapping("/equipamentos")
    public String viewEquipamentos(Model model) {
        model.addAttribute("listEquipamentos", equipamentoService.retrieveAll());

        return "equipamentos";
    }

    @GetMapping("/equipamentos/create")
    public String viewEquipamentosCreateForm(Equipamento equipamento) {
        return "equipamentos-create-form";
    }

    @PostMapping("/equipamentos/save")
    public String saveEquipamento(@ModelAttribute Equipamento equipamento, Model model) {
        equipamentoService.append(equipamento);

        model.addAttribute("listEquipamentos", equipamentoService.retrieveAll());

        return "redirect:/equipamentos";
    }

    @GetMapping("/equipamentos/edit/{id}")
    public String editEquipamento(@PathVariable("id") Integer id, Model model){
        var equipamento = equipamentoService.retrieveById(id);

        model.addAttribute("equipamento", equipamento);

        return "equipamentos-edit-form";
    }

    @PostMapping("/equipamentos/patch/{id}")
    public String putEquipamento(@PathVariable("id") Integer id, @ModelAttribute Equipamento equipamento, Model model) {
        equipamentoService.alter(id, equipamento);

        return "redirect:/equipamentos";
    }

    @GetMapping("/equipamentos/delete/{id}")
    public String deleteEquipamento(@PathVariable("id") Integer id, Model model){
        equipamentoService.remove(id);

        model.addAttribute("listEquipamentos", equipamentoService.retrieveAll());

        return "redirect:/equipamentos";
    }
}
