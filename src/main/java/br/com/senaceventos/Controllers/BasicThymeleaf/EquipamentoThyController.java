package br.com.senaceventos.Controllers.BasicThymeleaf;

import br.com.senaceventos.Services.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EquipamentoThyController {

    private EquipamentoService equipamentoService;

    @Autowired
    public EquipamentoThyController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @RequestMapping("/equipamento")
    public String viewEquipamentos(Model model) {
        model.addAttribute("listEquipamentos", equipamentoService.retrieveAll());
        return "equipamentos";
    }
}
