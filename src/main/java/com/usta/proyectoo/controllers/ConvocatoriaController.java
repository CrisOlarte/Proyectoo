package com.usta.proyectoo.controllers;

import com.usta.proyectoo.entities.Convocatoria;
import com.usta.proyectoo.models.services.ConvocatoriaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
//
@Controller
@RequestMapping("/convocatorias")
public class ConvocatoriaController {

    @Autowired
    private ConvocatoriaServices convocatoriaServices;

    // Mostrar formulario de creación
    @GetMapping("/crear")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("convocatoria", new Convocatoria());
        return "convocatoria/crear"; // nombre del HTML sin extensión
    }

    // Guardar convocatoria
    @PostMapping("/guardar")
    public String guardarConvocatoria(@Valid @ModelAttribute("convocatoria") Convocatoria convocatoria,
                                      BindingResult result,
                                      Model model) {
        if (result.hasErrors()) {
            return "convocatoria/crear";
        }

        convocatoriaServices.save(convocatoria);
        return "redirect:/convocatorias/listar";
    }

    // Mostrar todas las convocatorias (opcional)
    @GetMapping("/listar")
    public String listarConvocatorias(Model model) {
        model.addAttribute("convocatorias", convocatoriaServices.findAll());
        return "convocatoria/listar"; // aún no lo tienes, pero puedes crearlo luego
    }
    @GetMapping
    public String verGestionConvocatorias(Model model) {
        model.addAttribute("convocatorias", convocatoriaServices.findAll());
        return "convocatoria/listarConvocatorias";
    }


}
