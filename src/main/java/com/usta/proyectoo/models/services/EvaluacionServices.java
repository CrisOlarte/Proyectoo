package com.usta.proyectoo.models.services;


import com.usta.proyectoo.entities.Evaluacion;
import com.usta.proyectoo.entities.Startup;
import com.usta.proyectoo.entities.Usuario;
import jakarta.transaction.Transactional;

import java.util.List;

public interface EvaluacionServices {
    List<Evaluacion> findAll();
    void save(Evaluacion evaluacion);
    Evaluacion findById(Long id);
    void deleteById(Evaluacion evaluacion);
    Evaluacion actualizar(Evaluacion evaluacion);
    List<Evaluacion> findByStartup(Long idStartup);
    List<Evaluacion> findByUsuario(Long idUsuario);
    List<Evaluacion> findByStartupAndUsuario(Startup startup, Usuario usuario);
    Evaluacion findByUsuarioAndStartup(Usuario usuario, Startup startup);


}
