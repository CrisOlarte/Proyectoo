package com.usta.proyectoo.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import java.io.IOException;
import java.util.Collection;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        // Flash message
        SessionFlashMapManager flashMapManager = new SessionFlashMapManager();
        FlashMap flashMap = new FlashMap();
        flashMap.put("success", "Has iniciado sesión exitosamente " + authentication.getName());
        flashMapManager.saveOutputFlashMap(flashMap, request, response);

        // Redirección por rol
        String redirectUrl = "/";

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();

            if (role.equals("ROLE_ADMIN")) {
                redirectUrl = "/dashboard/administrativo";
                break;
            } else if (role.equals("ROLE_EMPRENDEDOR")) {
                redirectUrl = "/dashboard/estudiante";
                break;
            } else if (role.equals("ROLE_EVALUADOR")) {
                redirectUrl = "/dashboard/evaluador";
                break;
            }
        }

        // Redirigir a la URL correspondiente
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
