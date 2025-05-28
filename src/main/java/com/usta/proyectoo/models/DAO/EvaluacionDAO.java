package com.usta.proyectoo.models.DAO;


import com.usta.proyectoo.entities.Evaluacion;
import com.usta.proyectoo.entities.Startup;
import com.usta.proyectoo.entities.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EvaluacionDAO extends CrudRepository<Evaluacion, Long> {

    @Transactional
    @Query("SELECT e FROM Evaluacion e WHERE e.startup.idStartup = ?1")
    List<Evaluacion> findByStartup(Long idStartup);

    @Transactional
    @Query("SELECT e FROM Evaluacion e WHERE e.usuario.idUsuario = ?1")
    List<Evaluacion> findByUsuario(Long idUsuario);

    @Transactional
    @Query("SELECT e FROM Evaluacion e WHERE e.startup = ?1 AND e.usuario = ?2")
    List<Evaluacion> findByStartupAndUsuario(Startup startup, Usuario usuario);

    @Transactional
    @Query("SELECT e FROM Evaluacion e WHERE e.usuario = ?1 AND e.startup = ?2")
    Evaluacion findByUsuarioAndStartup(Usuario usuario, Startup startup);

}
