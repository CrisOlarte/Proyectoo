package comustaproyectoo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/inicio")
    public String mostrarInicio() {
        return "inicio"; // Renderiza el archivo inicio.html en templates/
    }
}
