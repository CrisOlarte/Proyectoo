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
        return "dashboard/dashboardAdministrativo";
    }

    // Dashboard para usuario emprendedor
    @GetMapping("/emprendedor")
    public String dashboardEmprendedor(Model model) {
        return "dashboard/dashboardEmprendedor";
    }

    // Ruta general opcional (puedes redirigir según rol aquí si quieres lógica más avanzada)
    @GetMapping
    public String dashboardGeneral() {
        return "redirect:/dashboard/administrativo"; // o emprendedor, según lo que desees
    }
}
