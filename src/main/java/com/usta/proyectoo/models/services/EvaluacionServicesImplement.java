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

    @Override
    @Transactional
    public List<Evaluacion> findAll() {
        return (List<Evaluacion>) evaluacionDAO.findAll();
    }

    @Override
    @Transactional
    public void save(Evaluacion evaluacion) {
        evaluacionDAO.save(evaluacion);
    }

    @Override
    @Transactional
    public Evaluacion findById(Long id) {
        return evaluacionDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Evaluacion evaluacion) {
        evaluacionDAO.delete(evaluacion);
    }

    @Override
    @Transactional
    public Evaluacion actualizar(Evaluacion evaluacion) {
        return evaluacionDAO.save(evaluacion);
    }

    @Override
    @Transactional
    public List<Evaluacion> findByStartup(Long idStartup) {
        return evaluacionDAO.findByStartup(idStartup);
    }

    @Override
    @Transactional
    public List<Evaluacion> findByUsuario(Long idUsuario) {
        return evaluacionDAO.findByUsuario(idUsuario);
    }

    @Override
    @Transactional
    public List<Evaluacion> findByStartupAndUsuario(Startup startup, Usuario usuario) {
        return evaluacionDAO.findByStartupAndUsuario(startup, usuario);
    }

    @Override
    @Transactional
    public Evaluacion findByUsuarioAndStartup(Usuario usuario, Startup startup) {
        return evaluacionDAO.findByUsuarioAndStartup(usuario, startup);
    }
}
