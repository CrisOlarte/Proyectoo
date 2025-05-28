package com.usta.proyectoo.models.DAO;


import com.usta.proyectoo.entities.Convocatoria;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConvocatoriaDAO extends CrudRepository<Convocatoria, Long> {

    @Transactional
    @Query("SELECT c FROM Convocatoria c ORDER BY c.fechaCreacion DESC")
    List<Convocatoria> findAllOrderedByFechaCreacion();

    @Transactional
    @Query("SELECT c FROM Convocatoria c WHERE c.estado = ?1")
    List<Convocatoria> findByEstado(String estado);
}


// NADA SIRVE, DIEGO ES TONTO MMM