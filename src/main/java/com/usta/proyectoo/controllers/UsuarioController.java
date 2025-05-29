package com.usta.proyectoo.controllers;

import com.usta.proyectoo.models.services.UsuarioServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {
    private final UsuarioServices usuarioServices;

    public UsuarioController(UsuarioServices usuarioServices) {
        this.usuarioServices = usuarioServices;
    }

    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioServices.findAll()); // Asegúrate de tener este método
        return "usuarios/listarUsuarios";
    }

}


