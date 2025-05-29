package com.usta.proyectoo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {
    @GetMapping(value = "/Inicio")
    public String verInicio(Model model) {
        model.addAttribute("title", "Startup Hub - Inicio");
        return "inicio/Inicio";
    }
}