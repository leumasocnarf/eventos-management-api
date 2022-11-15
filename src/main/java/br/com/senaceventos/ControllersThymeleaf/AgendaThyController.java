package br.com.senaceventos.ControllersThymeleaf;

import br.com.senaceventos.Entities.Agenda;
import br.com.senaceventos.Entities.Colaborador;
import br.com.senaceventos.Entities.Equipamento;
import br.com.senaceventos.Entities.Local;
import br.com.senaceventos.Services.AgendaService;
import br.com.senaceventos.Services.ColaboradorService;
import br.com.senaceventos.Services.EquipamentoService;
import br.com.senaceventos.Services.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AgendaThyController {
    
    private final AgendaService agendaService;
    private final EquipamentoService equipamentoService;
    private final ColaboradorService colaboradorService;
    private final LocalService localService;


    @Autowired
    public AgendaThyController(AgendaService agendaService, EquipamentoService equipamentoService, ColaboradorService colaboradorService, LocalService localService) {
        this.agendaService = agendaService;
        this.equipamentoService = equipamentoService;
        this.colaboradorService = colaboradorService;
        this.localService = localService;
    }

    @GetMapping("/agendas")
    public String viewAgendas(Model model) {
        model.addAttribute("listAgendas", agendaService.retrieveAll());

        return "agendas";
    }

    @GetMapping("/agendas/create")
    public String viewAgendasCreateForm(Agenda agenda, Model model) {
        var colaboradores = colaboradorService.retrieveAll();
        var locais = localService.retrieveAll();
        var equipamentos = equipamentoService.retrieveAll();

        model.addAttribute("agenda", new Agenda());
        model.addAttribute("colaboradores", colaboradores);
        model.addAttribute("locais", locais);
        model.addAttribute("equipamentos", equipamentos);

        return "agendas-create-form";
    }

    @PostMapping("/agendas/save")
    public String saveAgenda(@ModelAttribute Agenda agenda,
                             Model model) {

        agendaService.append(agenda);

        model.addAttribute("listAgendas", agendaService.retrieveAll());

        return "redirect:/agendas";
    }

    @GetMapping("/agendas/edit/{id}")
    public String editAgenda(@PathVariable("id") Integer id, Model model){
        var agenda = agendaService.retrieveById(id);
        var colaboradores = colaboradorService.retrieveAll();
        var locais = localService.retrieveAll();
        var equipamentos = equipamentoService.retrieveAll();

        model.addAttribute("agenda", agenda);
        model.addAttribute("colaboradores", colaboradores);
        model.addAttribute("locais", locais);
        model.addAttribute("equipamentos", equipamentos);

        return "agendas-edit-form";
    }

    @PostMapping("/agendas/patch/{id}")
    public String putAgenda(@PathVariable("id") Integer id, @ModelAttribute Agenda agenda, Model model) {
        agendaService.alter(id, agenda);

        return "redirect:/agendas";
    }

    @GetMapping("/agendas/delete/{id}")
    public String deleteAgenda(@PathVariable("id") Integer id, Model model){
        agendaService.remove(id);

        model.addAttribute("listAgendas", agendaService.retrieveAll());

        return "redirect:/agendas";
    }
}
