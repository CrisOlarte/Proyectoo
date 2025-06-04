package com.usta.proyectoo.models.services;

import com.usta.proyectoo.entities.Startup;

import java.util.Date;
import java.util.List;

public interface StartupServices {
    List<Startup> findAll();
    void save(Startup startup);
    Startup findById(Long id);
    void deleteById(Long id);
    Startup actualizar(Startup startup);
    List<Startup> findByNombre(String nombre);
    List<Startup> findBySector(String sector);
    List<Startup> findByUbicacion(String ubicacion);
    List<Startup> findByEstado(Boolean estado);
    List<Startup> findByFechaCreacionAfter(Date fecha);
    List<Startup> findByValoracionMayorA(Double valorMinimo);

}
