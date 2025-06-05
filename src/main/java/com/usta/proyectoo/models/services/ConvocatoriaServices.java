package com.usta.proyectoo.models.services;

import com.usta.proyectoo.entities.Convocatoria;
import com.usta.proyectoo.entities.Startup;
import com.usta.proyectoo.entities.Usuario;

import java.util.List;

public interface ConvocatoriaServices {
    List<Convocatoria> findAll();
    Convocatoria findById(Long id);
    void save(Convocatoria convocatoria);
    void deleteById(Long id);
    Convocatoria actualizar(Convocatoria convocatoria);
    List<Startup> findByUsuario(Usuario usuario);
    List<Startup> findAllActivas();

    // NUEVO
    Convocatoria obtenerConvocatoriaActual();
}

