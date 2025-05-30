package com.usta.proyectoo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistroController {

    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "registro"; // Apunta a src/main/resources/templates/registro.html
    }
}
