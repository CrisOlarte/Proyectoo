package com.usta.proyectoo.controllers;

import com.usta.proyectoo.entities.Rol;
import com.usta.proyectoo.entities.Usuario;
import com.usta.proyectoo.models.services.RolesServices;
import com.usta.proyectoo.models.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;

    @Autowired
    private RolesServices rolesServices;

    @GetMapping("/crear")
    public String mostrarFormularioRegistro(Model model) {
        Usuario usuario = new Usuario();
        usuario.setRol(new Rol()); // evitar null
        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", rolesServices.findAll());
        model.addAttribute("activePage", "usuarios");
        return "usuario/crearUsuario";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Rol.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String idStr) throws IllegalArgumentException {
                try {
                    Long id = Long.parseLong(idStr);
                    Rol rol = rolesServices.findById(id);
                    setValue(rol);
                } catch (NumberFormatException e) {
                    setValue(null);
                }
            }
        });
    }

    // ✅ Guardar nuevo usuario
    @PostMapping("/guardar")
    public String guardarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "usuario/crearUsuario"; // carpeta en singular
        }
        try {
            usuarioServices.save(usuario);
            redirectAttributes.addFlashAttribute("success", "Usuario creado exitosamente");
            return "redirect:/usuarios/listar";
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "Error: El correo ya está registrado");
            return "redirect:/usuarios/crear"; // Redirigir de nuevo al formulario
        }
    }

    // ✅ Listar todos los usuarios
    @GetMapping({"/listar", ""})
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioServices.findAll());
        model.addAttribute("activePage", "usuarios");
        return "usuario/listarUsuarios";
    }

    // ✅ Mostrar formulario para editar un usuario
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long idUsuario,
                                          Model model,
                                          RedirectAttributes redirectAttributes) {
        Usuario usuario = usuarioServices.findById(idUsuario);
        if (usuario == null) {
            redirectAttributes.addFlashAttribute("error", "Usuario no encontrado");
            return "redirect:/usuarios/listar";
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("activePage", "usuarios");
        model.addAttribute("title", "Editar Usuario");
        return "usuario/editarUsuario";
    }

    // ✅ Guardar cambios de edición
    @PostMapping("/editar/{id}")
    public String guardarCambiosEditar(@PathVariable("id") Long idUsuario,
                                       @Valid @ModelAttribute("usuario") Usuario usuario,
                                       BindingResult result,
                                       RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "usuario/editarUsuario";
        }

        Usuario usuarioExistente = usuarioServices.findById(idUsuario);
        if (usuarioExistente == null) {
            redirectAttributes.addFlashAttribute("error", "Usuario no encontrado");
            return "redirect:/usuarios/listar";
        }

        usuario.setIdUsuario(idUsuario);
        usuario.setFechaReg(usuarioExistente.getFechaReg());
        if (usuario.getContrasena() == null || usuario.getContrasena().isBlank()) {
            usuario.setContrasena(usuarioExistente.getContrasena());
        }

        usuarioServices.actualizar(usuario);
        redirectAttributes.addFlashAttribute("success", "Usuario actualizado con éxito");
        return "redirect:/usuarios/listar";
    }

    // ✅ Eliminar usuario
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") Long id,
                                  RedirectAttributes redirectAttributes) {
        Usuario usuario = usuarioServices.findById(id);
        if (usuario != null) {
            usuarioServices.deleteById(usuario);
            redirectAttributes.addFlashAttribute("success", "Usuario eliminado con éxito");
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuario no encontrado");
        }
        return "redirect:/usuarios/listar";
    }
}
