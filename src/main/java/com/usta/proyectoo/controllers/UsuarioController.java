package com.usta.proyectoo.controllers;

import com.usta.proyectoo.entities.Usuario;
import com.usta.proyectoo.models.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;

    // Mostrar formulario para crear usuario
    @GetMapping("/crear")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("activePage", "usuarios"); // 👈 Añadido
        return "usuarios/crearUsuario";
    }

    // Guardar un nuevo usuario
    @PostMapping("/guardar")
    public String guardarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario,
                                 BindingResult result,
                                 Model model) {
        model.addAttribute("activePage", "usuarios"); // 👈 Añadido

        if (result.hasErrors()) {
            return "usuarios/crearUsuario";
        }

        usuarioServices.save(usuario);
        return "redirect:/usuarios/listar";
    }

    // Listar todos los usuarios
    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioServices.findAll());
        model.addAttribute("activePage", "usuarios"); // 👈 Añadido
        return "usuarios/listarUsuarios";
    }

    // Vista principal de gestión de usuarios
    @GetMapping
    public String verGestionUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioServices.findAll());
        model.addAttribute("activePage", "usuarios"); // 👈 Añadido
        return "usuarios/listarUsuarios";
    }
}
