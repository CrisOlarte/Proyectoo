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
        return "usuarios/crearUsuario"; // Asegúrate de tener esta vista
    }

    // Guardar un nuevo usuario
    @PostMapping("/guardar")
    public String guardarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario,
                                 BindingResult result,
                                 Model model) {
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
        return "usuarios/listarUsuarios"; // Asegúrate de tener esta vista
    }

    // Vista principal de gestión de usuarios (opcional)
    @GetMapping
    public String verGestionUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioServices.findAll());
        return "usuarios/listarUsuarios";
    }
}
