package com.usta.proyectoo.controllers;

import com.usta.proyectoo.entities.Startup;
import com.usta.proyectoo.models.services.StartupServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/startups")
public class StartupController {

    @Autowired
    private StartupServices startupServices;

    // Mostrar formulario de creación de startup
    @GetMapping("/crear")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("startup", new Startup());
        model.addAttribute("activePage", "startups"); // ✅ Marca activa
        return "startups/crearStartup";
    }

    // Guardar una nueva startup
    @PostMapping("/guardar")
    public String guardarStartup(@ModelAttribute("startup") Startup startup, Model model) {
        model.addAttribute("activePage", "startups"); // ✅ Para evitar errores si hay redirección fallida
        startupServices.save(startup);
        return "redirect:/startups/listar";
    }

    // Listar todas las startups
    @GetMapping("/listar")
    public String listarStartups(Model model) {
        model.addAttribute("startups", startupServices.findAll());
        model.addAttribute("activePage", "startups"); // ✅
        return "startups/listarStartups";
    }

    // Vista principal de gestión de startups (opcional)
    @GetMapping
    public String verGestionStartups(Model model) {
        model.addAttribute("startups", startupServices.findAll());
        model.addAttribute("activePage", "startups"); // ✅
        return "startups/listarStartups";
    }
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long idStartup,
                                          Model model,
                                          RedirectAttributes redirectAttributes) {
        Startup startup = startupServices.findById(idStartup);
        if (startup == null) {
            redirectAttributes.addFlashAttribute("error", "Startup no encontrada");
            return "redirect:/startups/listar";
        }
        model.addAttribute("startup", startup);
        model.addAttribute("activePage", "startups");
        model.addAttribute("title", "Editar Startup");
        return "startups/editarStartup"; // Vista JSP o HTML que debes crear
    }
    @PostMapping("/editar/{id}")
    public String guardarCambiosEditar(@PathVariable("id") Long idStartup,
                                       @ModelAttribute("startup") Startup startup,
                                       RedirectAttributes redirectAttributes,
                                       Model model) {
        model.addAttribute("activePage", "startups");
        model.addAttribute("title", "Editar Startup");

        // Puedes validar datos aquí si usas @Valid y BindingResult

        startup.setIdStartup(idStartup); // Asegúrate de que el ID no se pierda
        startupServices.actualizar(startup); // Este método debe existir
        redirectAttributes.addFlashAttribute("success", "Startup actualizada con éxito");
        return "redirect:/startups/listar";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarStartup(@PathVariable("id") Long idStartup,
                                  RedirectAttributes redirectAttributes) {
        Startup startup = startupServices.findById(idStartup);
        if (startup != null) {
            startupServices.deleteById(idStartup); // Solo se pasa el ID
            redirectAttributes.addFlashAttribute("success", "Startup eliminada con éxito");
        } else {
            redirectAttributes.addFlashAttribute("error", "Startup no encontrada");
        }
        return "redirect:/startups/listar";
    }





}
