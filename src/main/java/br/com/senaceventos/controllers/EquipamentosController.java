package br.com.senaceventos.controllers;

import br.com.senaceventos.models.Equipamento;
import br.com.senaceventos.services.EquipamentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class EquipamentosController {

    private final EquipamentosService equipamentosService;

    @Autowired
    public EquipamentosController(EquipamentosService equipamentosService) {
        this.equipamentosService = equipamentosService;
    }

    @GetMapping("/equipamentos")
    public String viewEquipamentos(Model model) {
        model.addAttribute("listEquipamentos", equipamentosService.retrieveAll());

        return "equipamentos";
    }

    @GetMapping("/equipamentos/create")
    public String viewEquipamentosCreateForm(Equipamento equipamento) {
        return "equipamentos-create-form";
    }

    @PostMapping("/equipamentos/save")
    public String saveEquipamento(@ModelAttribute Equipamento equipamento, Model model) {
        equipamentosService.append(equipamento);

        model.addAttribute("listEquipamentos", equipamentosService.retrieveAll());

        return "redirect:/equipamentos";
    }

    @GetMapping("/equipamentos/edit/{id}")
    public String editEquipamento(@PathVariable("id") Integer id, Model model){
        var equipamento = equipamentosService.retrieveById(id);

        model.addAttribute("equipamento", equipamento);

        return "equipamentos-edit-form";
    }

    @PostMapping("/equipamentos/patch/{id}")
    public String putEquipamento(@PathVariable("id") Integer id, @ModelAttribute Equipamento equipamento, Model model) {
        equipamentosService.alter(id, equipamento);

        return "redirect:/equipamentos";
    }

    @GetMapping("/equipamentos/delete/{id}")
    public String deleteEquipamento(@PathVariable("id") Integer id, Model model){
        equipamentosService.remove(id);

        model.addAttribute("listEquipamentos", equipamentosService.retrieveAll());

        return "redirect:/equipamentos";
    }
}
