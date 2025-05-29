package com.usta.proyectoo.models.services;

import com.usta.proyectoo.entities.Startup;
import com.usta.proyectoo.models.DAO.StartupDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class StartupServicesImplement implements StartupServices {

    @Autowired
    private StartupDao startupDao;

    @Override
    @Transactional
    public List<Startup> findAll() {
        return (List<Startup>) startupDao.findAll();
    }

    @Override
    @Transactional
    public void save(Startup startup) {
        startupDao.save(startup);
    }

    @Override
    @Transactional
    public Startup findById(Long id) {
        return startupDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Startup startup) {
        startupDao.delete(startup);
    }

    @Override
    @Transactional
    public Startup actualizar(Startup startup) {
        return startupDao.save(startup);
    }

    @Override
    @Transactional
    public List<Startup> findByNombre(String nombre) {
        return startupDao.findByNombre(nombre);
    }

    @Override
    @Transactional
    public List<Startup> findBySector(String sector) {
        return startupDao.findBySector(sector);
    }

    @Override
    @Transactional
    public List<Startup> findByUbicacion(String ubicacion) {
        return startupDao.findByUbicacion(ubicacion);
    }

    @Override
    @Transactional
    public List<Startup> findByEstado(Boolean estado) {
        return startupDao.findByEstado(estado);
    }

    @Override
    @Transactional
    public List<Startup> findByFechaCreacionAfter(Date fecha) {
        return startupDao.findByFechaCreacionAfter(fecha);
    }

    @Override
    @Transactional
    public List<Startup> findByValoracionMayorA(Double valorMinimo) {
        return startupDao.findByValoracionMayorA(valorMinimo);
    }
}
