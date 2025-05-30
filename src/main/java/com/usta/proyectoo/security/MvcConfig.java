package com.usta.proyectoo.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración personalizada para MVC que permite mapear URL directamente a vistas Thymeleaf
 * sin necesidad de crear controladores explícitos.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Mapea la URL /sobre-nosotros directamente a la vista "sobre-nosotros.html"
        registry.addViewController("/sobreNosotros").setViewName("sobreNosotros");
        registry.addViewController("/contacto").setViewName("contacto");
    }

}





