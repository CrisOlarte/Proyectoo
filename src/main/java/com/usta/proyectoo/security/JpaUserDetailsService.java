//package com.usta.proyectoo.security;
//
//import com.usta.proyectoo.entities.Rol;
//import com.usta.proyectoo.entities.Usuario;
//import com.usta.proyectoo.models.services.UsuarioServices;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.List;
//
//@Service("jpaUserDetailsService")
//public class JpaUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UsuarioServices usuarioServices;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Usuario usuario = usuarioServices.findByCorreo(username);
//
//        if (usuario == null) {
//            throw new UsernameNotFoundException("El usuario no existe");
//        }
//
//        System.out.println("Verificando rol...: " + usuario.getRol().getRol());
//
//        return new User(
//                usuario.getCorreo(),
//                usuario.getContrasena(),
//                mapearAutoridadesRol(usuario.getRol())
//        );
//    }
//
//    private Collection<? extends GrantedAuthority> mapearAutoridadesRol(Rol rol) {
//        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.getRol()));
//    }
//}
