package com.usta.proyectoo.models.services;

import com.usta.proyectoo.entities.Convocatoria;
import com.usta.proyectoo.entities.Startup;
import com.usta.proyectoo.entities.Usuario;
import com.usta.proyectoo.models.DAO.ConvocatoriaDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ConvocatoriaServicesImplement implements ConvocatoriaServices{

    @Autowired
    private ConvocatoriaDAO convocatoriaDAO;

    @Override
    @Transactional
    public List<Convocatoria> findAll() {
        return (List<Convocatoria>) convocatoriaDAO.findAll();
    }

    @Override
    @Transactional
    public Convocatoria findById(Long id) {
        return convocatoriaDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Convocatoria convocatoria) {
        convocatoriaDAO.save(convocatoria);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        convocatoriaDAO.deleteById(id);
    }

    @Override
    @Transactional
    public Convocatoria actualizar(Convocatoria convocatoria) {
        return convocatoriaDAO.save(convocatoria);
    }

    @Override
    @Transactional
    public List<Startup> findByUsuario(Usuario usuario) {
        return convocatoriaDAO.findByUsuario(usuario);
    }

    @Override
    @Transactional
    public List<Startup> findAllActivas() {
        return convocatoriaDAO.findAllActivas();
    }
}
