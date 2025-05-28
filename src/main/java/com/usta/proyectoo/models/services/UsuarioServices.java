package com.usta.proyectoo.models.services;

import com.usta.proyectoo.entities.Usuario;

import java.util.List;

public interface UsuarioServices {
    List<Usuario> findAll();
    void save(Usuario usuario);
    Usuario findById(Long id);
    void deleteById(Usuario usuario);
    Usuario actualizar(Usuario usuario);
    void changeStatus(Long id);
    Usuario findByCorreo(String correo);
    List<Usuario> findUsuariosActivos();
}
