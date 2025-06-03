package com.usta.proyectoo.controllers;

import com.usta.proyectoo.entities.Evaluacion;
import com.usta.proyectoo.models.services.EvaluacionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionServices evaluacionServices;

    // Mostrar formulario para crear evaluación
    @GetMapping("/crear")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("evaluacion", new Evaluacion());
        model.addAttribute("activePage", "evaluaciones"); // ✅
        return "evaluaciones/crearEvaluacion";
    }

    // Guardar una nueva evaluación
    @PostMapping("/guardar")
    public String guardarEvaluacion(@Valid @ModelAttribute("evaluacion") Evaluacion evaluacion,
                                    BindingResult result,
                                    Model model) {
        model.addAttribute("activePage", "evaluaciones"); // ✅
        if (result.hasErrors()) {
            return "evaluaciones/crearEvaluacion";
        }

        evaluacionServices.save(evaluacion);
        return "redirect:/evaluaciones/listar";
    }

    // Listar todas las evaluaciones
    @GetMapping("/listar")
    public String listarEvaluaciones(Model model) {
        model.addAttribute("evaluaciones", evaluacionServices.findAll());
        model.addAttribute("activePage", "evaluaciones"); // ✅
        return "evaluaciones/listarEvaluaciones";
    }

    // Vista principal de gestión de evaluaciones (opcional)
    @GetMapping
    public String verGestionEvaluaciones(Model model) {
        model.addAttribute("evaluaciones", evaluacionServices.findAll());
        model.addAttribute("activePage", "evaluaciones"); // ✅
        return "evaluaciones/listarEvaluaciones";
    }
}
