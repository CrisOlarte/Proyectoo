package com.usta.proyectoo.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotNull
    @Size(max = 100)
    private String nombre;

    @Size(max = 100)
    private String apellido;

    @NotNull
    @Email
    @Size(max = 100)
    private String correo;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String password;

    @Size(max = 20)
    private String telefono;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "ultimo_acceso")
    private LocalDateTime ultimoAcceso;

    @Column
    private Boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;
}
