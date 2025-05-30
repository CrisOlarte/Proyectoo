package com.usta.proyectoo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/inicio")
    public String mostrarInicio() {
        return "inicio"; // Renderiza el archivo inicio.html en templates/
    }
    @Controller
    public class LoginController {

        @GetMapping("/login")
        public String mostrarLogin() {
            return "login"; // Esto busca el archivo login.html en templates/
        }
    }

}
