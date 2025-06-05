package com.usta.proyectoo.models.services;

import com.usta.proyectoo.entities.Startup;
import com.usta.proyectoo.entities.Usuario;
import com.usta.proyectoo.models.DAO.StartupDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StartupServicesImplement implements StartupServices {

    @Autowired
    private StartupDAO startupDao;

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
    public void deleteById(Long id) {
        Startup startup = startupDao.findById(id).orElse(null);

        if (startup != null) {
            // Eliminar la relación de ambos lados (startup y usuario)
            for (Usuario usuario : startup.getUsuarios()) {
                usuario.getStartups().remove(startup); // limpia del lado de usuario
            }

            startup.getUsuarios().clear(); // limpia del lado de startup

            // Guardar ambos lados si es necesario (si no tienes CascadeType.ALL)
            // Esto puede omitirse si usas merge/persist en cascada.
            // usuarioDao.saveAll(usuarios); // opcional
            // startupDao.save(startup);     // opcional

            startupDao.deleteById(id);
        }
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
