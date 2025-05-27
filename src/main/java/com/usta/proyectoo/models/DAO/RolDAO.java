package com.usta.proyectoo.models.DAO;


import com.usta.proyectoo.entities.Rol;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RolDAO extends CrudRepository<Rol, Long> {

    @Transactional
    @Query("SELECT r FROM Rol r WHERE r.nombre = ?1")
    Rol findByNombre(String nombre);
}
