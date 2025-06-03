package com.usta.proyectoo.controllers;

import com.usta.proyectoo.entities.Convocatoria;
import com.usta.proyectoo.models.services.ConvocatoriaServices;
import com.usta.proyectoo.models.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/convocatorias")
public class ConvocatoriaController {

    @Autowired
    private ConvocatoriaServices convocatoriaServices;

    @Autowired
    private UsuarioServices usuarioServices;

    // Mostrar formulario de creación
    @GetMapping("/crear")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("convocatoria", new Convocatoria());
        model.addAttribute("activePage", "convocatorias"); // ✅
        return "convocatoria/crearConvocatoria";
    }

    // Guardar convocatoria
    @PostMapping("/guardar")
    public String guardarConvocatoria(@Valid @ModelAttribute("convocatoria") Convocatoria convocatoria,
                                      BindingResult result,
                                      Model model) {
        model.addAttribute("activePage", "convocatorias"); // ✅

        if (result.hasErrors()) {
            return "convocatoria/crearConvocatoria";
        }

        convocatoriaServices.save(convocatoria);
        return "redirect:/convocatorias/listar";
    }

    // Mostrar todas las convocatorias
    @GetMapping("/listar")
    public String listarConvocatorias(Model model) {
        model.addAttribute("convocatorias", convocatoriaServices.findAll());
        model.addAttribute("activePage", "convocatorias"); // ✅
        return "convocatoria/listarConvocatorias";
    }

    // Página principal de convocatorias (opcional)
    @GetMapping
    public String verGestionConvocatorias(Model model) {
        model.addAttribute("convocatorias", convocatoriaServices.findAll());
        model.addAttribute("activePage", "convocatorias"); // ✅
        return "convocatoria/listarConvocatorias";
    }
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long idConvocatoria,
                                          Model model,
                                          RedirectAttributes redirectAttributes) {
        Convocatoria convocatoria = convocatoriaServices.findById(idConvocatoria);
        if (convocatoria == null) {
            redirectAttributes.addFlashAttribute("error", "Convocatoria no encontrada");
            return "redirect:/convocatorias/listar";
        }
        model.addAttribute("convocatoria", convocatoria);
        model.addAttribute("activePage", "convocatorias");
        model.addAttribute("title", "Editar Convocatoria");
        return "convocatoria/editarConvocatoria";
    }

    @PostMapping("/editar/{id}")
    public String guardarCambiosEditar(@PathVariable("id") Long idConvocatoria,
                                       @Valid @ModelAttribute("convocatoria") Convocatoria convocatoria,
                                       BindingResult result,
                                       RedirectAttributes redirectAttributes,
                                       Model model) {
        model.addAttribute("activePage", "convocatorias");
        model.addAttribute("title", "Editar Convocatoria");

        if (result.hasErrors()) {
            return "convocatoria/editarConvocatoria";
        }

        convocatoria.setIdConvocatoria(idConvocatoria);
        convocatoriaServices.actualizar(convocatoria); // Asegúrate de tener este método en tu servicio
        redirectAttributes.addFlashAttribute("success", "Convocatoria actualizada con éxito");
        return "redirect:/convocatorias/listar";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarConvocatoria(@PathVariable("id") Long idConvocatoria,
                                       RedirectAttributes redirectAttributes) {
        Convocatoria convocatoria = convocatoriaServices.findById(idConvocatoria);
        if (convocatoria != null) {
            // Cambio: pasar el ID en lugar del objeto completo
            convocatoriaServices.deleteById(idConvocatoria);
            redirectAttributes.addFlashAttribute("success", "Convocatoria eliminada con éxito");
        } else {
            redirectAttributes.addFlashAttribute("error", "Convocatoria no encontrada");
        }
        return "redirect:/convocatorias/listar";
}


}
