package com.usta.proyectoo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {

        model.addAttribute("title", "Iniciar Sesión");

        if (error != null) {
            model.addAttribute("error",
                    "Credenciales incorrectas, por favor verifica tu correo y contraseña.");
        }

        if (logout != null) {
            model.addAttribute("success", "Has cerrado sesión exitosamente");
        }

        return "usuario/login"; // Asegúrate de que tengas templates/usuario/login.html
    }
}