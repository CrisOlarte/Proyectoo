package com.usta.proyectoo.models.DAO;


import com.usta.proyectoo.entities.Rol;
import com.usta.proyectoo.entities.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuarioDao extends CrudRepository<Usuario, Long> {

    @Transactional
    @Query("SELECT u FROM Usuario u WHERE u.correo = ?1")
    Usuario findByCorreo(String correo);

    @Transactional
    @Query("SELECT u FROM Usuario u WHERE u.estado = true")
    List<Usuario> findUsuariosActivos();

    @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.estado = false WHERE u.idUsuario = ?1")
    void changeStatus(Long idUsuario);
}