package com.usta.proyectoo.controllers;

import com.usta.proyectoo.models.services.EvaluacionServices;
import com.usta.proyectoo.models.services.UsuarioServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/usuarios")
@Controller
public class UsuarioController {

    private final UsuarioServices usuarioServices;
    private final EvaluacionServices evaluacionServices;

    public UsuarioController(UsuarioServices usuarioServices, EvaluacionServices evaluacionServices) {
        this.usuarioServices = usuarioServices;
        this.evaluacionServices = evaluacionServices;
    }

    @GetMapping("/listar")
    public String listarEvaluaciones(Model model) {
        model.addAttribute("evaluaciones", evaluacionServices.findAll());
        return "evaluacion/listar";
    }
}




