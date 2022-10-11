package br.com.senaceventos.Controllers.BasicThymeleaf;

import br.com.senaceventos.Services.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ColaboradorThyController {

    private ColaboradorService colaboradorService;

    @Autowired
    public ColaboradorThyController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @RequestMapping("/colaborador")
    public String viewColaboradores(Model model) {
        model.addAttribute("listColaboradores", colaboradorService.retrieveAll());
        return "colaboradores";
    }
}
