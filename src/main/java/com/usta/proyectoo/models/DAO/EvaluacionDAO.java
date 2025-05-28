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
    @Query("SELECT e FROM Evaluacion e WHERE e.evaluador.idUsuario = ?1")
    List<Evaluacion> findByEvaluador(Long idEvaluador);

    @Transactional
    @Query("SELECT e FROM Evaluacion e WHERE e.startup = ?1 AND e.faseEvaluacion = ?2")
    List<Evaluacion> findByStartupAndFase(Startup startup, String faseEvaluacion);

    @Transactional
    @Query("SELECT e FROM Evaluacion e WHERE e.evaluador = ?1 AND e.startup = ?2 AND e.faseEvaluacion = ?3")
    Evaluacion findByEvaluadorAndStartupAndFase(Usuario evaluador, Startup startup, String faseEvaluacion);


}
// DIEGO NO SABE NADA