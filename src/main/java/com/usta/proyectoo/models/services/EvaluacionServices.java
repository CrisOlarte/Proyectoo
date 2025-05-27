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
    Evaluacion buscarPorEvaluadorYStartupYFase(Usuario evaluador, Startup startup, String faseEvaluacion);
    List<Evaluacion> buscarPorStartup(Startup startup);
    List<Evaluacion> buscarPorFaseEvaluador(Usuario evaluador);
    List<Evaluacion> buscarPorStartupYFase(Startup startup, String faseEvaluacion);
    List<Evaluacion> buscarPorEvaluador(Usuario evaluador);
}
