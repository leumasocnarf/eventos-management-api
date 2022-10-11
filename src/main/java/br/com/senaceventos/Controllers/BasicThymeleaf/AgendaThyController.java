package br.com.senaceventos.Controllers.BasicThymeleaf;

import br.com.senaceventos.Services.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AgendaThyController {
    
    private AgendaService agendaService;

    @Autowired
    public AgendaThyController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @RequestMapping("/agenda")
    public String viewAgendas(Model model) {
        model.addAttribute("listAgendas", agendaService.retrieveAll());
        return "agendas";
    }
}
