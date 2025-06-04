package com.usta.proyectoo.security;

import com.usta.proyectoo.models.DAO.UsuarioDAO;
import com.usta.proyectoo.entities.Usuario;
import com.usta.proyectoo.entities.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDAO.findByCorreo(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("El usuario no existe");
        }

        // Verificar si el usuario está activo
        if (!usuario.getEstado()) {
            throw new UsernameNotFoundException("El usuario está inactivo");
        }

        System.out.println("Verificando rol...: " + usuario.getRol().getRol());

        return new User(
                usuario.getCorreo(),
                usuario.getContrasena(),
                mapearAutoridadesRol(usuario.getRol())
        );
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRol(Rol rol) {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.getRol()));

    }
}