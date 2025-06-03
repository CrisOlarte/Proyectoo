package com.usta.proyectoo.controllers;

import com.usta.proyectoo.entities.Usuario;
import com.usta.proyectoo.models.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;

    @GetMapping("/crear")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("activePage", "usuarios"); // ✅
        return "usuarios/crearUsuario";
    }


    // ✅ Guardar nuevo usuario
    @PostMapping("/guardar")
    public String guardarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario,
                                 BindingResult result,
                                 Model model) {
        model.addAttribute("activePage", "usuarios");
        model.addAttribute("title", "Nuevo Usuario");

        if (result.hasErrors()) {
            return "usuarios/crearUsuario";
        }

        usuarioServices.save(usuario);
        return "redirect:/usuarios/listar";
    }

    // ✅ Listar todos los usuarios
    @GetMapping({"/listar", ""})
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioServices.findAll());
        model.addAttribute("activePage", "usuarios");
        return "usuarios/listarUsuarios";
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
        return "usuarios/editarUsuario";
    }

    // ✅ Guardar cambios de edición
    @PostMapping("/editar/{id}")
    public String guardarCambiosEditar(@PathVariable("id") Long idUsuario,
                                       @Valid @ModelAttribute("usuario") Usuario usuario,
                                       BindingResult result,
                                       RedirectAttributes redirectAttributes,
                                       Model model) {
        model.addAttribute("activePage", "usuarios");
        model.addAttribute("title", "Editar Usuario");

        if (result.hasErrors()) {
            return "usuarios/editarUsuario";
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
    public String eliminarUsuario(@PathVariable("id") Long idUsuario,
                                  RedirectAttributes redirectAttributes) {
        Usuario usuario = usuarioServices.findById(idUsuario);
        if (usuario != null) {
            usuarioServices.deleteById(usuario);
            redirectAttributes.addFlashAttribute("success", "Usuario eliminado con éxito");
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuario no encontrado");
        }
        return "redirect:/usuarios/listar";
    }
}
