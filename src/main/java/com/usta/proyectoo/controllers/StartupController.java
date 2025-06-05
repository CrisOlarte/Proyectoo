package com.usta.proyectoo.controllers;

import com.usta.proyectoo.entities.Convocatoria;
import com.usta.proyectoo.entities.Evaluacion;
import com.usta.proyectoo.entities.Startup;
import com.usta.proyectoo.entities.Usuario;
import com.usta.proyectoo.models.services.ConvocatoriaServices;
import com.usta.proyectoo.models.services.StartupServices;
import com.usta.proyectoo.models.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/startups")
public class StartupController {

    @Autowired
    private StartupServices startupServices;
    @Autowired
    private UsuarioServices usuarioServices;
    @Autowired
    private ConvocatoriaServices convocatoriaServices;

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
    @GetMapping("/listar2")
    public String listarStartupsH(Model model) {
        model.addAttribute("startups", startupServices.findAll());
        model.addAttribute("activePage", "startups"); // ✅
        return "startups/evaluarStartups";
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
        System.out.println("➡️ Intentando eliminar startup con ID: " + idStartup);
        Startup startup = startupServices.findById(idStartup);
        if (startup != null) {
            startupServices.deleteById(idStartup);
            System.out.println("✅ Eliminada startup: " + startup.getNombre()); // o lo que tenga
            redirectAttributes.addFlashAttribute("success", "Startup eliminada con éxito");
        } else {
            System.out.println("❌ Startup no encontrada");
            redirectAttributes.addFlashAttribute("error", "Startup no encontrada");
        }
        return "redirect:/startups/listar";
    }
    @GetMapping("/evaluar/{id}")
    public String evaluarStartupPorId(@PathVariable("id") Long idStartup, Model model, Principal principal, RedirectAttributes redirectAttributes) {
        Startup startup = startupServices.findById(idStartup);
        if (startup == null) {
            redirectAttributes.addFlashAttribute("error", "Startup no encontrada para evaluación.");
            return "redirect:/startups/listar";
        }

        Usuario usuario = usuarioServices.findByCorreo(principal.getName());

        // ✅ Aquí obtienes todas las convocatorias (puedes filtrar solo las activas si quieres)
        List<Convocatoria> convocatorias = convocatoriaServices.findAll();  // o .obtenerConvocatoriasActivas()

        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setStartup(startup);
        evaluacion.setUsuario(usuario);

        model.addAttribute("evaluacion", evaluacion);
        model.addAttribute("startup", startup);
        model.addAttribute("convocatorias", convocatorias); // 🔥 importante para el combobox
        model.addAttribute("activePage", "evaluacion");

        return "startups/evaluacionStartup";
    }



// === VISTA MIS STARTUPS ===
    @GetMapping("/misStartups")
    public String misStartupsView(Model model) {
        model.addAttribute("activePage", "mis-startups"); // Marca activa para la vista
        return "startups/misStartups"; // Archivo .html que se debe mostrar
    }

// === ESTUDIANTE - POSTULAR ===
    @GetMapping("/postulaciones")
    public String vistaPostulacionStartup(Model model) {
        model.addAttribute("activePage", "postulaciones"); // Marca el ítem activo en el sidebar
        return "startups/postulacionStartup"; // Ruta de la plantilla HTML
    }







}
