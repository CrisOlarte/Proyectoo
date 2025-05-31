package com.usta.proyectoo.controllers;

import com.usta.proyectoo.entities.Evaluacion;
import com.usta.proyectoo.models.services.EvaluacionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EvaluacionController {


    @Autowired
    private EvaluacionServices evaluacionService;

    @GetMapping("/listar")
    public String listarEvaluaciones(Model model) {
        model.addAttribute("evaluaciones", evaluacionService.findAll());
        return "evaluaciones/listarEvaluaciones"; // aún no lo tienes, pero puedes crearlo luego
    }
}
