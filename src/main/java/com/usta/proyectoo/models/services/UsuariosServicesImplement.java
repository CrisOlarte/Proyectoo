package com.usta.proyectoo.models.services;

import com.usta.proyectoo.entities.Usuario;
import com.usta.proyectoo.models.DAO.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuariosServicesImplement implements UsuarioServices {

    @Autowired
    private UsuarioDAO usuarioDao;

    @Override
    @Transactional
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioDao.findAll();
    }

    @Override
    @Transactional
    public void save(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    @Override
    @Transactional
    public Usuario findById(Long id) {
        return usuarioDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    @Override
    @Transactional
    public Usuario actualizar(Usuario usuario) {
        return usuarioDao.save(usuario);
    }

    @Override
    @Transactional
    public void changeStatus(Long id) {
        usuarioDao.changeStatus(id);
    }

    @Override
    @Transactional
    public Usuario findByCorreo(String correo) {
        return usuarioDao.findByCorreo(correo);
    }

    @Override
    @Transactional
    public List<Usuario> findUsuariosActivos() {
        return usuarioDao.findUsuariosActivos();
    }

}
