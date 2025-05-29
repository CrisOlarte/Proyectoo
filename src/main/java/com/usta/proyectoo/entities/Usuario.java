package com.usta.proyectoo.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotNull
    @Size(min = 1, max = 100)
    private String nombre;

    @Size(min = 1, max = 100)
    private String apellido;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "correo", nullable = false, unique = true)
    private String correo;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "telefono", nullable = false)
    private String telefono;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_reg", nullable = false)
    private Date fechaReg;

    @Column(name = "ultimo_acceso")
    private LocalDateTime ultimoAcceso;

    @NotNull
    @Column(name = "estado_usu", nullable = false)
    private Boolean estado;

    @NotNull
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Rol rol;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuarios_startups",
            joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_startup", referencedColumnName = "id_startup")
    )
    private Collection<Startup> startups = new ArrayList<>();
}
