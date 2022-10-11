package br.com.senaceventos.Controllers.BasicThymeleaf;

import br.com.senaceventos.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsuarioThyController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioThyController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @RequestMapping("/usuario")
    public String viewLocais(Model model) {
        model.addAttribute("listUsuarios", usuarioService.retrieveAll());
        return "usuarios";
    }
}
