package com.usta.proyectoo.models.DAO;


import com.usta.proyectoo.entities.Startup;
import com.usta.proyectoo.entities.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StartupDao extends CrudRepository<Startup, Long> {

    @Transactional
    @Query("SELECT s FROM Startup s WHERE s.emprendedor = ?1")
    List<Startup> findByEmprendedor(Usuario emprendedor);

    @Transactional
    @Query("SELECT s FROM Startup s WHERE s.convocatoria = ?1")
    List<Startup> findByConvocatoria(Usuario convocatoria);
}
