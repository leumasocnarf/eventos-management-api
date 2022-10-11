package br.com.senaceventos.Controllers.BasicThymeleaf;

import br.com.senaceventos.Services.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LocalThyController {

    private LocalService localService;

    @Autowired
    public LocalThyController(LocalService localService) {
        this.localService = localService;
    }

    @RequestMapping("/local")
    public String viewLocais(Model model) {
        model.addAttribute("listLocais", localService.retrieveAll());
        return "locais";
    }
}
