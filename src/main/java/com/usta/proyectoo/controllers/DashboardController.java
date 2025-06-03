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

    // Dashboard para usuario emprendedor
    @GetMapping("/emprendedor")
    public String dashboardEmprendedor(Model model) {
        model.addAttribute("activePage", "dashboard"); // 👈 También se marca dashboard
        return "dashboard/dashboardEmprendedor";
    }

    // Ruta general
    @GetMapping
    public String dashboardGeneral() {
        return "redirect:/dashboard/administrativo"; // Puedes redirigir según rol
    }
}
