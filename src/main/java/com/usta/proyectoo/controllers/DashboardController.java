package com.usta.proyectoo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    // Dashboard para usuario administrativo
    @GetMapping("/administrativo")
    public String dashboardAdministrativo(Model model) {
        model.addAttribute("activePage", "dashboard"); // 👈 Aquí marcamos el ítem activo
        return "dashboard/dashboardAdministrativo";
    }

    // Dashboard para usuario estudiante
    @GetMapping("/estudiante")
    public String dashboardEstudiante(Model model) {
        model.addAttribute("activePage", "dashboard"); // 👈 Aquí marcamos el ítem activo
        return "dashboard/dashboardEstudiante";
    }



}
