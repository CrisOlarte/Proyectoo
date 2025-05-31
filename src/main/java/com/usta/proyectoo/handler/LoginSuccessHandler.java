package com.usta.proyectoo.handler;
/*
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import java.io.IOException;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        // Obtener el nombre de usuario
        String nombreUsuario = authentication.getName(); // ← Este método existe en org.springframework.security.core.Authentication

        // Crear mensaje flash
        SessionFlashMapManager flashMapManager = new SessionFlashMapManager();
        FlashMap flashMap = new FlashMap();
        flashMap.put("success", "Has iniciado sesión exitosamente, " + nombreUsuario);
        flashMapManager.saveOutputFlashMap(flashMap, request, response);

        // Redirección por defecto
        super.onAuthenticationSuccess(request, response, authentication);
    }
}*/