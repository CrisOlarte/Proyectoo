package com.usta.proyectoo.models.DAO;


import com.usta.proyectoo.entities.Startup;
import com.usta.proyectoo.entities.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface StartupDao extends CrudRepository<Startup, Long> {

    @Transactional
    @Query("SELECT s FROM Startup s WHERE s.nombre = ?1")
    List<Startup> findByNombre(String nombre);

    @Transactional
    @Query("SELECT s FROM Startup s WHERE s.sector = ?1")
    List<Startup> findBySector(String sector);

    @Transactional
    @Query("SELECT s FROM Startup s WHERE s.ubicacion = ?1")
    List<Startup> findByUbicacion(String ubicacion);

    @Transactional
    @Query("SELECT s FROM Startup s WHERE s.estado = ?1")
    List<Startup> findByEstado(Boolean estado);

    @Transactional
    @Query("SELECT s FROM Startup s WHERE s.fechaCreacion >= ?1")
    List<Startup> findByFechaCreacionAfter(Date fecha);

    @Transactional
    @Query("SELECT s FROM Startup s WHERE s.valoracion >= ?1")
    List<Startup> findByValoracionMayorA(Double valorMinimo);

    @Transactional
    @Query("SELECT s FROM Startup s ORDER BY s.valoracion DESC")
    List<Startup> findTopValoradas();
}
