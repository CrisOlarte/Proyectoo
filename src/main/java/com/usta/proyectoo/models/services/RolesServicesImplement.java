package com.usta.proyectoo.models.services;

import com.usta.proyectoo.entities.Rol;
import com.usta.proyectoo.models.DAO.RolDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServicesImplement implements RolesServices {

    @Autowired
    private RolDAO rolDAO;

    @Override
    @Transactional
    public List<Rol> findAll() {
        return (List<Rol>) rolDAO.findAll();
    }

    @Override
    @Transactional
    public void save(Rol rol) {
        rolDAO.save(rol);
    }

    @Override
    @Transactional
    public Rol findById(Long id) {
        return rolDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Rol rol) {
        rolDAO.delete(rol);
    }

    @Override
    @Transactional
    public Rol actualizar(Rol rol) {
        return rolDAO.save(rol);
    }

    @Override
    @Transactional
    public Rol findByNombre(String nombre) {
        return rolDAO.findByNombre(nombre);
    }

}
