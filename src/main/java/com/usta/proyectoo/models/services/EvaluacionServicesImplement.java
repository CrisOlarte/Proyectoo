package com.usta.proyectoo.models.services;


import com.usta.proyectoo.entities.Evaluacion;
import com.usta.proyectoo.entities.Startup;
import com.usta.proyectoo.entities.Usuario;
import com.usta.proyectoo.models.DAO.EvaluacionDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluacionServicesImplement implements EvaluacionServices {

    @Autowired
    private EvaluacionDAO evaluacionDAO;

    @Transactional
    @Override
    public List<Evaluacion> findAll() {
        return (List<Evaluacion>) evaluacionDAO.findAll();
    }

    @Transactional
    @Override
    public void save(Evaluacion evaluacion) {
        evaluacionDAO.save(evaluacion);
    }

    @Transactional
    @Override
    public Evaluacion findById(Long id) {
        return evaluacionDAO.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void deleteById(Evaluacion evaluacion) {
        evaluacionDAO.delete(evaluacion);
    }

    @Transactional
    @Override
    public Evaluacion actualizar(Evaluacion evaluacion) {
        return evaluacionDAO.save(evaluacion);
    }

    @Transactional
    @Override
    public Evaluacion buscarPorEvaluadorYStartupYFase(Usuario evaluador, Startup startup, String faseEvaluacion) {
        return evaluacionDAO.findByEvaluadorAndStartupAndFase(evaluador, startup, faseEvaluacion);
    }

    @Transactional
    @Override
    public List<Evaluacion> buscarPorStartup(Startup startup) {
        return evaluacionDAO.findByStartupAndFase(startup, null);
    }

    @Override
    public List<Evaluacion> buscarPorFaseEvaluador(Usuario evaluador) {
        return List.of();
    }

    @Override
    public List<Evaluacion> buscarPorStartupYFase(Startup startup, String faseEvaluacion) {
        return List.of();
    }

    @Transactional
    @Override
    public List<Evaluacion> buscarPorEvaluador(Usuario evaluador) {
        return evaluacionDAO.findByEvaluador(evaluador.getIdUsuario());
    }

}
