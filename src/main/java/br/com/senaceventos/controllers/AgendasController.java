package br.com.senaceventos.controllers;

import br.com.senaceventos.models.Agenda;
import br.com.senaceventos.services.AgendasService;
import br.com.senaceventos.services.ColaboradoresService;
import br.com.senaceventos.services.EquipamentosService;
import br.com.senaceventos.services.LocaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AgendasController {
    
    private final AgendasService agendasService;
    private final EquipamentosService equipamentosService;
    private final ColaboradoresService colaboradoresService;
    private final LocaisService locaisService;


    @Autowired
    public AgendasController(AgendasService agendasService, EquipamentosService equipamentosService, ColaboradoresService colaboradoresService, LocaisService locaisService) {
        this.agendasService = agendasService;
        this.equipamentosService = equipamentosService;
        this.colaboradoresService = colaboradoresService;
        this.locaisService = locaisService;
    }

    @GetMapping("/agendas")
    public String viewAgendas(Model model) {
        model.addAttribute("listAgendas", agendasService.retrieveAll());

        return "agendas";
    }

    @GetMapping("/agendas/create")
    public String viewAgendasCreateForm(Agenda agenda, Model model) {
        var colaboradores = colaboradoresService.retrieveAll();
        var locais = locaisService.retrieveAll();
        var equipamentos = equipamentosService.retrieveAll();

        model.addAttribute("agenda", new Agenda());
        model.addAttribute("colaboradores", colaboradores);
        model.addAttribute("locais", locais);
        model.addAttribute("equipamentos", equipamentos);

        return "agendas-create-form";
    }

    @PostMapping("/agendas/save")
    public String saveAgenda(@ModelAttribute Agenda agenda,
                             Model model) {

        agendasService.append(agenda);

        model.addAttribute("listAgendas", agendasService.retrieveAll());

        return "redirect:/agendas";
    }

    @GetMapping("/agendas/edit/{id}")
    public String editAgenda(@PathVariable("id") Integer id, Model model){
        var agenda = agendasService.retrieveById(id);
        var colaboradores = colaboradoresService.retrieveAll();
        var locais = locaisService.retrieveAll();
        var equipamentos = equipamentosService.retrieveAll();

        model.addAttribute("agenda", agenda);
        model.addAttribute("colaboradores", colaboradores);
        model.addAttribute("locais", locais);
        model.addAttribute("equipamentos", equipamentos);

        return "agendas-edit-form";
    }

    @PostMapping("/agendas/patch/{id}")
    public String putAgenda(@PathVariable("id") Integer id, @ModelAttribute Agenda agenda, Model model) {
        agendasService.alter(id, agenda);

        return "redirect:/agendas";
    }

    @GetMapping("/agendas/delete/{id}")
    public String deleteAgenda(@PathVariable("id") Integer id, Model model){
        agendasService.remove(id);

        model.addAttribute("listAgendas", agendasService.retrieveAll());

        return "redirect:/agendas";
    }
}
