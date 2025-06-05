package com.usta.proyectoo.controllers;

import com.usta.proyectoo.entities.Evaluacion;
import com.usta.proyectoo.models.services.EvaluacionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;

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
    public String guardarEvaluacion(
            @Valid @ModelAttribute("evaluacion") Evaluacion evaluacion,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {
        model.addAttribute("activePage", "evaluacion");

        if (result.hasErrors()) {
            model.addAttribute("startup", evaluacion.getStartup());
            return "startups/evaluacionStartup";
        }

// ✅ Establecer la fecha actual como fecha de evaluación
        evaluacion.setFechaEvaluacion(java.sql.Date.valueOf(LocalDate.now()));

        evaluacionServices.save(evaluacion);
        redirectAttributes.addFlashAttribute("success", "Evaluación guardada correctamente.");
        return "redirect:/evaluaciones";
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

    // Vista de evaluaciones asignadas al evaluador
    @GetMapping("/mis-evaluaciones")
    public String mostrarMisEvaluaciones(Model model) {
        model.addAttribute("activePage", "mis-evaluaciones");
        return "evaluaciones/misEvaluaciones";
    }
    // ✅ Vista: Evaluar startups asignadas
    @GetMapping("/evaluar")
    public String mostrarFormularioEvaluacion(Model model) {
        model.addAttribute("activePage", "evaluar");
        return "evaluaciones/evaluarStartups"; // Ruta de la vista HTML
    }
    // ✅ Vista: Historial de evaluaciones realizadas por el evaluador
    @GetMapping("/historial")
    public String verHistorialEvaluaciones(Model model) {
        model.addAttribute("activePage", "historial-evaluaciones");
        return "evaluaciones/historialStartups"; // Nombre de la vista .html
    }



}
