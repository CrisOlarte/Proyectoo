package com.usta.proyectoo.models.services;

import com.usta.proyectoo.entities.Rol;

import java.util.List;

public interface RolesServices {
    List<Rol> findAll();
    void save(Rol rol);
    Rol findById(Long id);
    void deleteById(Rol rol);
    Rol actualizar(Rol rol);
    Rol findByNombre(String nombre);
}
