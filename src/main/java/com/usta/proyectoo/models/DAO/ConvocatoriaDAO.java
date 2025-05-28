package com.usta.proyectoo.models.DAO;


import com.usta.proyectoo.entities.Convocatoria;
import com.usta.proyectoo.entities.Startup;
import com.usta.proyectoo.entities.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConvocatoriaDAO extends CrudRepository<Convocatoria, Long> {

    @Transactional
    @Query("SELECT s FROM Startup s JOIN s.usuarios u WHERE u = ?1")
    List<Startup> findByUsuario(Usuario usuario);

    @Transactional
    @Query("SELECT s FROM Startup s WHERE s.estado = true")
    List<Startup> findAllActivas();

}
