package com.usta.proyectoo.controllers;

import com.usta.proyectoo.entities.Startup;
import com.usta.proyectoo.models.services.StartupServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/startups")
public class StartupController {

    @Autowired
    private StartupServices startupServices;

    // Mostrar formulario de creación de startup
    @GetMapping("/crear")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("startup", new Startup());
        return "startups/crearStartup";
    }

    // Guardar una nueva startup
    @PostMapping("/guardar")
    public String guardarStartup(@ModelAttribute("startup") Startup startup) {
        startupServices.save(startup);
        return "redirect:/startups/listar";
    }

    // Listar todas las startups
    @GetMapping("/listar")
    public String listarStartups(Model model) {
        model.addAttribute("startups", startupServices.findAll());
        return "startups/listarStartups";
    }

    // Vista principal de gestión de startups (opcional)
    @GetMapping
    public String verGestionStartups(Model model) {
        model.addAttribute("startups", startupServices.findAll());
        return "startups/listarStartups";
    }
}
