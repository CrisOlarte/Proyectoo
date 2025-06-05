package com.usta.proyectoo.controllers;

import com.usta.proyectoo.entities.Evaluacion;
import com.usta.proyectoo.entities.Usuario;
import com.usta.proyectoo.models.services.EvaluacionServices;
import com.usta.proyectoo.models.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.Valid;
import java.util.List;

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

    // Vista de evaluaciones asignadas al evaluador

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

    @GetMapping("/misEvaluaciones")
    public String misEvaluacionesView(Model model) {
        model.addAttribute("activePage", "mis-evaluaciones"); // útil para marcar en el menú
        return "evaluaciones/misEvaluaciones"; // Ruta a la plantilla HTML
    }




}
